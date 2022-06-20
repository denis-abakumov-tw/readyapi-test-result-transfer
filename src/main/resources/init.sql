drop table if exists test_type CASCADE;
drop table if exists test_suite CASCADE;
drop table if exists test_case CASCADE;
drop table if exists test_environment CASCADE;
drop table if exists test_suite_execution CASCADE;
drop table if exists test_case_execution CASCADE;
drop table if exists performance_result CASCADE;
drop function if exists get_executions(text, text, text, text, timestamp);

create table test_type
(
    id   serial unique primary key,
    name varchar(255) unique not null
);

create table test_suite
(
    id           serial unique primary key,
    test_type_id int                 not null,
    name         varchar(255) unique not null,
    CONSTRAINT fk_test_type
        FOREIGN KEY (test_type_id)
            REFERENCES test_type (id)
);

create table test_case
(
    id            serial unique primary key,
    name          varchar(255) unique not null,
    test_suite_id int                 not null,
    CONSTRAINT fk_test_suite
        FOREIGN KEY (test_suite_id)
            REFERENCES test_suite (id)
);

create table test_environment
(
    id   serial unique primary key,
    name varchar(255) unique not null
);

create table test_suite_execution
(
    id                  serial unique primary key,
    start_time          timestamp(0),
    test_suite_id       int not null,
    test_environment_id int not null,
    CONSTRAINT fk_test_case
        FOREIGN KEY (test_suite_id)
            REFERENCES test_suite (id),
    CONSTRAINT fk_test_environment
        FOREIGN KEY (test_environment_id)
            REFERENCES test_environment (id),
    constraint test_suite_execution_unique
        unique (start_time, test_suite_id, test_environment_id)
);

create table test_case_execution
(
    id                      serial unique primary key,
    test_suite_execution_id int not null,
    test_case_id            int not null,
    CONSTRAINT fk_test_suite_execution
        FOREIGN KEY (test_suite_execution_id)
            REFERENCES test_suite_execution (id),
    CONSTRAINT fk_test_case
        FOREIGN KEY (test_case_id)
            REFERENCES test_case (id)
);

create table performance_result
(
    id                     serial unique primary key,
    test_case_execution_id int     not null,
    time_sec               numeric not null,
    min_ms                 numeric default 0,
    max_ms                 numeric default 0,
    median_ms              numeric default 0,
    last_ms                numeric default 0,
    cnt                    numeric default 0,
    tps                    numeric default 0,
    err                    numeric default 0,
    CONSTRAINT fk_test_execution
        FOREIGN KEY (test_case_execution_id)
            REFERENCES test_case_execution (id)
);

create or replace function get_executions(test_type_name text, test_suite_name text, test_case_name text,
                                          test_environment_name text,
                                          execution_start_time timestamp)
    returns table
            (
                date_time timestamp,
                min_ms    numeric,
                max_ms    numeric,
                median_ms numeric,
                last_ms   numeric,
                cnt       numeric,
                tps       numeric,
                err       numeric
            )
as
$$
SELECT to_timestamp(extract(epoch from execution_start_time) + 36000 + time_sec) AS "time",
       min_ms                                                                    AS "Min Request Time (ms)",
       max_ms                                                                    AS "Max Request Time (ms)",
       median_ms                                                                 AS "Median Request Time (ms)",
       last_ms                                                                   AS "Last Request (ms)",
       cnt                                                                       AS "Request Counts",
       tps                                                                       AS "Tx per Second",
       err                                                                       AS "Errors"
FROM performance_result
where test_case_execution_id = (select id
                                from test_case_execution
                                where test_case_id = (select id
                                                      from test_case
                                                      where name = test_case_name)
                                  and test_suite_execution_id = (select id
                                                                 from test_suite_execution
                                                                 where test_environment_id =
                                                                       (select id from test_environment where name = test_environment_name)
                                                                   and start_time = execution_start_time
                                                                   and test_suite_id =
                                                                       (select id
                                                                        from test_suite
                                                                        where name = test_suite_name
                                                                          and test_type_id = (select id from test_type where name = test_type_name))))
order by time_sec;
$$
    language sql;