insert into test_type (name) values ('Functional');
insert into test_type (name) values ('Security');
insert into test_type (name) values ('Performance');

insert into test_suite (test_type_id, name) values (3, 'KPI Service Test');
insert into test_suite (test_type_id, name) values (3, 'Space Booking Service Test');
insert into test_suite (test_type_id, name) values (3, 'User Service Test');

insert into test_case (name, test_suite_id) values ('REST:/kpi/getActionCards:/kpi/getActionCards', 1);
insert into test_case (name, test_suite_id) values ('REST:/kpi/getActionCards:Total', 1);
insert into test_case (name, test_suite_id) values ('GraphQL:announcement and announcementFilters:announcement and announcementFilters', 1);
insert into test_case (name, test_suite_id) values ('GraphQL:announcement and announcementFilters:Total', 1);
insert into test_case (name, test_suite_id) values ('GraphQL:parking and parkingInfo:parking and parkingInfo', 1);
insert into test_case (name, test_suite_id) values ('GraphQL:parking and parkingInfo:Total', 1);

insert into test_environment (name) values ('Dev');
insert into test_environment (name) values ('QA');

insert into test_suite_execution (start_time, test_suite_id, test_environment_id) values ('2022-06-15 21:10:03', 1, 1);
insert into test_suite_execution (start_time, test_suite_id, test_environment_id) values ('2022-07-13 11:20:05', 2, 2);
insert into test_suite_execution (start_time, test_suite_id, test_environment_id) values ('2022-08-10 01:30:07', 3, 1);

insert into test_case_execution (test_suite_execution_id, test_case_id) values (1, 1);
insert into test_case_execution (test_suite_execution_id, test_case_id) values (1, 2);
insert into test_case_execution (test_suite_execution_id, test_case_id) values (1, 3);
insert into test_case_execution (test_suite_execution_id, test_case_id) values (1, 4);
insert into test_case_execution (test_suite_execution_id, test_case_id) values (1, 5);
insert into test_case_execution (test_suite_execution_id, test_case_id) values (1, 6);

insert into performance_result (test_case_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,0,9,21,20,11,48,36,21);
insert into performance_result (test_case_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,1,10,32,20,12,58,34,32);
insert into performance_result (test_case_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,2,10,30,20,11,68,46,30);
insert into performance_result (test_case_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,3,11,30,20,12,79,37,30);
insert into performance_result (test_case_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,4,9,30,20,12,88,34,30);
insert into performance_result (test_case_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,5,11,30,20,11,99,34,30);
insert into performance_result (test_case_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,6,9,30,20,11,108,41,30);
insert into performance_result (test_case_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,7,11,30,21,12,119,42,30);
insert into performance_result (test_case_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,8,10,30,19,11,129,37,30);
insert into performance_result (test_case_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,9,10,30,21,14,139,39,30);
