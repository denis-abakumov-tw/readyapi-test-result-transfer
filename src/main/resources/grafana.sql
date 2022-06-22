-- Panel Query
select date_time AS "time",
       tps       AS "Rate (req/s)",
       err       AS "Errors (req/s)",
       last_ms   AS "Duration (ms)"
from get_executions('Performance',
                    '${PerformanceTest}',
                    '${Scenario}',
                    '${TestEnvironment}',
                    '${StartTime}');

-- Variable: TestEnvironment
select name
from test_environment;

-- Variable: PerformanceTest
select name
from performance_test
where id in (select performance_test_id
             from performance_test_execution
             where test_environment_id in (select id from test_environment where name = '${TestEnvironment}'))
  and test_type_id = (select id from test_type where name = 'Performance');

-- Scenario
select name
from scenario
where id in (select scenario_id
             from scenario_execution
             where performance_test_execution_id in (select id
                                               from performance_test_execution
                                               where performance_test_id in (select id
                                                                       from performance_test
                                                                       where name = '${PerformanceTest}'
                                                                         and test_type_id = (select id from test_type where name = 'Performance')
                                                                         and test_environment_id =
                                                                             (select id from test_environment where name = '${TestEnvironment}'))));

-- StartTime
select to_char(start_time, 'YYYY-MM-DD HH24:MI:SS')
from performance_test_execution
where test_environment_id = (select id from test_environment where name = '${TestEnvironment}')
  and performance_test_id = (select id from performance_test where name = '${PerformanceTest}');
