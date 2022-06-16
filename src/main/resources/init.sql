drop table if exists test_type CASCADE;
drop table if exists test_case CASCADE;
drop table if exists test_station CASCADE;
drop table if exists test_execution CASCADE;
drop table if exists performance_metric CASCADE;
drop table if exists performance_result CASCADE;

create table test_type
(
    id   serial unique primary key,
    name varchar(255) unique not null
);

create table test_case
(
    id           serial unique primary key,
    test_type_id int                 not null,
    name         varchar(255) unique not null,
    CONSTRAINT fk_test_type
        FOREIGN KEY (test_type_id)
            REFERENCES test_type (id)
);

create table test_station
(
    id   serial unique primary key,
    name varchar(255) unique not null
);

create table test_execution
(
    id              serial unique primary key,
    started         timestamp(3),
    test_case_id    int not null,
    test_station_id int not null,
    CONSTRAINT fk_test_case
        FOREIGN KEY (test_case_id)
            REFERENCES test_case (id),
    CONSTRAINT fk_test_station
        FOREIGN KEY (test_station_id)
            REFERENCES test_station (id)
);

create table performance_metric
(
    id   serial unique primary key,
    name varchar(255) unique not null
);

create table performance_result
(
    id                    serial unique primary key,
    test_execution_id     int     not null,
    performance_metric_id int     not null,
    seconds               numeric not null,
    amount                numeric,
    CONSTRAINT fk_test_execution
        FOREIGN KEY (test_execution_id)
            REFERENCES test_execution (id),
    CONSTRAINT fk_performance_metric
        FOREIGN KEY (performance_metric_id)
            REFERENCES performance_metric (id)
);

SELECT setval('test_type_id_seq', (SELECT MAX(id) FROM test_type) + 1);
SELECT setval('test_station_id_seq', (SELECT MAX(id) FROM test_station) + 1);
SELECT setval('test_case_id_seq', (SELECT MAX(id) FROM test_case) + 1);
SELECT setval('test_execution_id_seq', (SELECT MAX(id) FROM test_execution) + 1);
