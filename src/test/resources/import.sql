insert into test_type (name) values ('Functional');
insert into test_type (name) values ('Security');
insert into test_type (name) values ('Performance');

insert into performance_test (test_type_id, name) values (3, 'KPI Service Test');
insert into performance_test (test_type_id, name) values (3, 'Space Booking Service Test');
insert into performance_test (test_type_id, name) values (3, 'User Service Test');

insert into scenario (name) values ('REST');
insert into scenario (name) values ('GraphQL');

insert into test_case (name) values ('/kpi/getActionCards');
insert into test_case (name) values ('announcement and announcementFilters');
insert into test_case (name) values ('parking and parkingInfo:parking and parkingInfo');
insert into test_case (name) values ('parking and parkingInfo:Total');

insert into test_step (name) values ('/kpi/getActionCards');
insert into test_step (name) values ('Total');
insert into test_step (name) values ('announcement and announcementFilters');
insert into test_step (name) values ('parking and parkingInfo');

insert into test_environment (name) values ('Dev');
insert into test_environment (name) values ('QA');

insert into performance_test_execution (start_time, performance_test_id, test_environment_id) values ('2022-06-15 21:10:03', 1, 1);
insert into performance_test_execution (start_time, performance_test_id, test_environment_id) values ('2022-07-13 11:20:05', 2, 2);
insert into performance_test_execution (start_time, performance_test_id, test_environment_id) values ('2022-08-10 01:30:07', 3, 1);

insert into scenario_execution (performance_test_execution_id, scenario_id) values (1, 1);
insert into scenario_execution (performance_test_execution_id, scenario_id) values (1, 2);
insert into scenario_execution (performance_test_execution_id, scenario_id) values (1, 1);
insert into scenario_execution (performance_test_execution_id, scenario_id) values (1, 2);
insert into scenario_execution (performance_test_execution_id, scenario_id) values (1, 1);
insert into scenario_execution (performance_test_execution_id, scenario_id) values (1, 2);

insert into test_case_execution (scenario_execution_id, test_case_id) values (1, 1);
insert into test_case_execution (scenario_execution_id, test_case_id) values (1, 2);
insert into test_case_execution (scenario_execution_id, test_case_id) values (1, 3);
insert into test_case_execution (scenario_execution_id, test_case_id) values (1, 4);

insert into test_step_execution (test_case_execution_id, test_step_id) values (1, 1);
insert into test_step_execution (test_case_execution_id, test_step_id) values (1, 2);
insert into test_step_execution (test_case_execution_id, test_step_id) values (1, 3);
insert into test_step_execution (test_case_execution_id, test_step_id) values (1, 4);
insert into test_step_execution (test_case_execution_id, test_step_id) values (2, 1);
insert into test_step_execution (test_case_execution_id, test_step_id) values (2, 2);

insert into test_step_execution_metrics (test_step_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,0,9,21,20,11,48,36,21);
insert into test_step_execution_metrics (test_step_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,1,10,32,20,12,58,34,32);
insert into test_step_execution_metrics (test_step_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,2,10,30,20,11,68,46,30);
insert into test_step_execution_metrics (test_step_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,3,11,30,20,12,79,37,30);
insert into test_step_execution_metrics (test_step_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,4,9,30,20,12,88,34,30);
insert into test_step_execution_metrics (test_step_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,5,11,30,20,11,99,34,30);
insert into test_step_execution_metrics (test_step_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,6,9,30,20,11,108,41,30);
insert into test_step_execution_metrics (test_step_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,7,11,30,21,12,119,42,30);
insert into test_step_execution_metrics (test_step_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,8,10,30,19,11,129,37,30);
insert into test_step_execution_metrics (test_step_execution_id, time_sec, min_ms, max_ms, median_ms, last_ms, cnt, tps, err) values (1,9,10,30,21,14,139,39,30);
