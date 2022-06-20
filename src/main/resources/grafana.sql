-- Panel Query
select date_time AS "time",
       tps       AS "Rate (req/s)",
       err       AS "Errors (req/s)",
       last_ms   AS "Duration (ms)"
from get_executions('Performance',
                    '${TestSuite}',
                    '${TestCase}',
                    '${TestEnvironment}',
                    '${StartTime}');

-- Variable: TestEnvironment
select name
from test_environment;

-- Variable: TestSuite
select name
from test_suite
where id in (select test_suite_id
             from test_suite_execution
             where test_environment_id in (select id from test_environment where name = '${TestEnvironment}'))
  and test_type_id = (select id from test_type where name = 'Performance');

-- TestCase
select name
from test_case
where id in (select test_case_id
             from test_case_execution
             where test_suite_execution_id in (select id
                                               from test_suite_execution
                                               where test_suite_id in (select id
                                                                       from test_suite
                                                                       where name = '${TestSuite}'
                                                                         and test_type_id = (select id from test_type where name = 'Performance')
                                                                         and test_environment_id =
                                                                             (select id from test_environment where name = '${TestEnvironment}'))));

-- StartTime
select to_char(start_time, 'YYYY-MM-DD HH24:MI:SS')
from test_suite_execution
where test_environment_id = (select id from test_environment where name = '${TestEnvironment}')
  and test_suite_id = (select id from test_suite where name = '${TestSuite}');
