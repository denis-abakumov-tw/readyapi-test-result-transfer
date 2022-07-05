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

-- Variable: _TestEnvironmentID
select id
from test_environment
where name = '$TestEnvironment';

-- Variable: PerformanceTest
select name
from performance_test
where id in (select performance_test_id
             from performance_test_execution
             where test_environment_id = '${_TestEnvironmentID}');

-- Variable: _PerformanceTestID
select id
from performance_test
where name = '${PerformanceTest}';

-- Variable: StartTime
select to_char(start_time, 'YYYY-MM-DD HH24:MI:SS')
from performance_test_execution
where test_environment_id = '$_TestEnvironmentID'
  and performance_test_id = '$_PerformanceTestID';

-- Variable: _PerformanceTestExecutionID
select id
from performance_test_execution
where performance_test_id = '$_PerformanceTestID'
  and start_time = '$StartTime'
  and test_environment_id = '$_TestEnvironmentID';

-- Variable: TestStep
select name
from test_step ts
         join test_step_execution tse on ts.id = tse.test_step_id
where tse.test_case_execution_id in
      (select id
       from test_case_execution
       where scenario_execution_id in
             (select id
              from scenario_execution
              where performance_test_execution_id = '$_PerformanceTestExecutionID'));

-- Variable: _TestStepExecutionID
select tse.id
from test_step_execution tse
         join test_step ts on ts.id = tse.test_step_id
         join test_case_execution tce on tse.test_case_execution_id = tce.id
         join test_case tc on tc.id = tce.test_case_id
         join scenario_execution se on se.id = tce.scenario_execution_id
         join scenario s on s.id = se.scenario_id
         join performance_test_execution pte on pte.id = se.performance_test_execution_id
         join performance_test pt on pt.id = pte.performance_test_id
where ts.id = '$_TestStepID'
and pte.id = '$_PerformanceTestExecutionID';

-- Variable: Scenario
select name
from scenario
where id in (select scenario_id
             from scenario_execution
             where performance_test_execution_id in (select id
                                                     from performance_test_execution
                                                     where performance_test_id = '$_PerformanceTestID'));

-- Variable: _ScenarioID
select id
from scenario
where name = '${Scenario}';

-- Variable: TestCase
select name
from test_case
where id in (select test_case_id
             from test_case_execution
             where scenario_execution_id in (select id
                                             from scenario_execution
                                             where scenario_id = '${_ScenarioID}'));

-- Variable: _TestCaseID
select id
from test_case
where name = '${TestCase}';

