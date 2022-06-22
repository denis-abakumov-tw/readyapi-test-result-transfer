drop table if exists test_type CASCADE;
drop table if exists test_environment CASCADE;
drop table if exists test_step CASCADE;
drop table if exists test_step_execution CASCADE;
drop table if exists test_step_execution_metrics CASCADE;
drop table if exists performance_test CASCADE;
drop table if exists performance_test_execution CASCADE;
drop table if exists performance_test_execution_result CASCADE;
drop table if exists scenario CASCADE;
drop table if exists test_case CASCADE;
drop table if exists test_case_execution CASCADE;
drop table if exists test_case_execution_statistics CASCADE;
drop table if exists scenario_execution CASCADE;
drop table if exists scenario_execution_result CASCADE;
drop table if exists test_step_execution_statistics CASCADE;
drop table if exists test_step_execution_metrics CASCADE;

drop function if exists get_step_execution_metrics(text, text, text, text, timestamp);

create table test_type
(
    id   serial unique primary key,
    name varchar(255) unique not null
);

create table test_environment
(
    id   serial unique primary key,
    name varchar(255) unique not null
);

create table performance_test
(
    id           serial unique primary key,
    test_type_id int                 not null,
    name         varchar(255) unique not null,
    CONSTRAINT fk_performance_test__test_type_id
        FOREIGN KEY (test_type_id)
            REFERENCES test_type (id)
);

create table performance_test_execution
(
    id                  serial unique primary key,
    start_time          timestamp(0),
    performance_test_id int not null,
    test_environment_id int not null,
    CONSTRAINT fk_performance_test_execution__performance_test_id
        FOREIGN KEY (performance_test_id)
            REFERENCES performance_test (id),
    CONSTRAINT fk_performance_test_execution__test_environment_id
        FOREIGN KEY (test_environment_id)
            REFERENCES test_environment (id),
    constraint performance_test_execution_unique
        unique (start_time, performance_test_id, test_environment_id)
);

create table performance_test_execution_result
(
    id                            serial unique primary key,
    performance_test_execution_id int not null,
    CONSTRAINT fk_performance_test_execution_result__test_execution_id
        FOREIGN KEY (performance_test_execution_id)
            REFERENCES performance_test_execution (id)
);

create table scenario
(
    id   serial unique primary key,
    name varchar(255) unique not null
);

create table scenario_execution
(
    id                            serial unique primary key,
    performance_test_execution_id int not null,
    scenario_id                   int not null,
    CONSTRAINT fk_scenario_execution__performance_test_execution_id
        FOREIGN KEY (performance_test_execution_id)
            REFERENCES performance_test_execution (id),
    CONSTRAINT fk_scenario_execution__scenario_id
        FOREIGN KEY (scenario_id)
            REFERENCES scenario (id)
);

create table scenario_execution_result
(
    id                    serial unique primary key,
    scenario_execution_id int not null,
    CONSTRAINT fk_scenario_execution_result__scenario_execution_id
        FOREIGN KEY (scenario_execution_id)
            REFERENCES scenario_execution (id)
);

create table test_case
(
    id   serial unique primary key,
    name varchar(255) unique not null
);

create table test_case_execution
(
    id                    serial unique primary key,
    scenario_execution_id int not null,
    test_case_id          int not null,
    CONSTRAINT fk_test_case_execution__scenario_execution_id
        FOREIGN KEY (scenario_execution_id)
            REFERENCES scenario_execution (id),
    CONSTRAINT fk_test_case_execution__test_case_id
        FOREIGN KEY (test_case_id)
            REFERENCES test_case (id)
);

create table test_case_execution_statistics
(
    id                     serial unique primary key,
    test_case_execution_id int not null,
    cnt                    numeric default 0,
    min                    numeric default 0,
    max                    numeric default 0,
    avg                    numeric default 0,
    std_dev                numeric default 0,
    min_avg                numeric default 0,
    max_avg                numeric default 0,
    failure                numeric default 0,
    failure_ratio          numeric default 0,
    percentile75           numeric default 0,
    percentile90           numeric default 0,
    percentile95           numeric default 0,
    percentile98           numeric default 0,
    median                 numeric default 0,
    last                   numeric default 0,
    tps                    numeric default 0,
    CONSTRAINT fk_test_case_execution_statistics__test_case_execution_id
        FOREIGN KEY (test_case_execution_id)
            REFERENCES test_case_execution (id)
);

create table test_step
(
    id   serial unique primary key,
    name varchar(255) unique not null
);

create table test_step_execution
(
    id                     serial unique primary key,
    test_case_execution_id int not null,
    test_step_id           int not null,
    CONSTRAINT fk_test_step_execution__test_case_execution_id
        FOREIGN KEY (test_case_execution_id)
            REFERENCES test_case_execution (id),
    CONSTRAINT fk_test_step_execution__test_step_id
        FOREIGN KEY (test_step_id)
            REFERENCES test_step (id)
);

create table test_step_execution_metrics
(
    id                     serial unique primary key,
    test_step_execution_id int     not null,
    time_sec               numeric not null,
    min_ms                 numeric default 0,
    max_ms                 numeric default 0,
    median_ms              numeric default 0,
    last_ms                numeric default 0,
    cnt                    numeric default 0,
    tps                    numeric default 0,
    err                    numeric default 0,
    CONSTRAINT fk_test_step_execution_metrics__test_step_execution_id
        FOREIGN KEY (test_step_execution_id)
            REFERENCES test_step_execution (id)
);

create table test_step_execution_statistics
(
    id                     serial unique primary key,
    test_step_execution_id int not null,
    cnt                    numeric default 0,
    min                    numeric default 0,
    max                    numeric default 0,
    avg                    numeric default 0,
    failure                numeric default 0,
    failure_ratio          numeric default 0,
    percentile75           numeric default 0,
    percentile90           numeric default 0,
    percentile95           numeric default 0,
    percentile98           numeric default 0,
    median                 numeric default 0,
    last                   numeric default 0,
    tps                    numeric default 0,
    CONSTRAINT fk_test_step_execution_statistics__test_step_execution_id
        FOREIGN KEY (test_step_execution_id)
            REFERENCES test_step_execution (id)
);


create or replace function get_step_execution_metrics(test_type_name text, performance_test_name text, scenario_name text,
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
FROM test_step_execution_metrics
where test_step_execution_id = (select id
                                from scenario_execution
                                where scenario_id = (select id
                                                     from scenario
                                                     where name = scenario_name)
                                  and performance_test_execution_id = (select id
                                                                       from performance_test_execution
                                                                       where test_environment_id =
                                                                             (select id from test_environment where name = test_environment_name)
                                                                         and start_time = execution_start_time
                                                                         and performance_test_id =
                                                                             (select id
                                                                              from performance_test
                                                                              where name = performance_test_name
                                                                                and test_type_id = (select id from test_type where name = test_type_name))))
order by time_sec;
$$
    language sql;