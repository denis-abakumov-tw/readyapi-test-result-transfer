insert into test_type (id, name) values (1, 'Functional');
insert into test_type (id, name) values (2, 'Security');
insert into test_type (id, name) values (3, 'Performance');

insert into test_case (id, test_type_id, name) values (1, 3, 'KPI Service Test');
insert into test_case (id, test_type_id, name) values (2, 3, 'Space Booking Service Test');
insert into test_case (id, test_type_id, name) values (3, 3, 'User Service Test');

insert into test_station (id, name) values (1, 'Machine 1');
insert into test_station (id, name) values (2, 'Machine 2');

insert into performance_metric (id, name) values (1, 'Failures/s');
insert into performance_metric (id, name) values (2, 'Test case runs/s');
insert into performance_metric (id, name) values (3, 'Arriving VU/s');
insert into performance_metric (id, name) values (4, 'Running VU');
insert into performance_metric (id, name) values (5, 'Failures');
insert into performance_metric (id, name) values (6, 'Time Taken Last');

insert into test_execution (id, started, test_case_id, test_station_id) values (1, '2022-06-15 21:10:03', 1, 1);
insert into test_execution (id, started, test_case_id, test_station_id) values (2, '2022-07-13 11:20:05', 2, 2);
insert into test_execution (id, started, test_case_id, test_station_id) values (3, '2022-08-10 01:30:07', 3, 1);

insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (1, 1, 2, 0, 21);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (2, 1, 2, 1, 32);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (3, 1, 2, 2, 30);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (4, 1, 2, 3, 30);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (5, 1, 2, 4, 30);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (6, 1, 2, 5, 30);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (7, 1, 2, 6, 30);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (8, 1, 2, 7, 30);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (9, 1, 2, 8, 30);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (10, 1, 2, 9, 30);

insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (11, 1, 3, 0, 21);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (12, 1, 3, 1, 20);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (13, 1, 3, 2, 20);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (14, 1, 3, 3, 20);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (15, 1, 3, 4, 20);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (16, 1, 3, 5, 20);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (17, 1, 3, 6, 19);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (18, 1, 3, 7, 21);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (19, 1, 3, 8, 20);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (20, 1, 3, 9, 20);

insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (21, 1, 4, 0, 12);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (22, 1, 4, 1, 12);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (23, 1, 4, 2, 12);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (24, 1, 4, 3, 12);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (25, 1, 4, 4, 12);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (26, 1, 4, 5, 11);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (27, 1, 4, 6, 10);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (28, 1, 4, 7, 12);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (29, 1, 4, 8, 11);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (30, 1, 4, 9, 11);

insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (31, 1, 6, 0, 33);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (32, 1, 6, 1, 35);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (33, 1, 6, 2, 84);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (34, 1, 6, 3, 36);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (35, 1, 6, 4, 34);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (36, 1, 6, 5, 34);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (37, 1, 6, 6, 31);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (38, 1, 6, 7, 41);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (39, 1, 6, 8, 39);
insert into performance_result (id, test_execution_id, performance_metric_id, seconds, amount) values (40, 1, 6, 9, 35);
