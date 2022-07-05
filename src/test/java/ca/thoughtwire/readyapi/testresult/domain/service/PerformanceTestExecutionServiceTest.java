package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.PerformanceTestExecution;
import ca.thoughtwire.readyapi.testresult.domain.model.TestEnvironment;
import ca.thoughtwire.readyapi.testresult.domain.model.PerformanceTest;
import ca.thoughtwire.readyapi.testresult.domain.model.TestType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceTestExecutionServiceTest {

    @Autowired
    private PerformanceTestExecutionService performanceTestExecutionService;

    @Autowired
    private PerformanceTestService performanceTestService;

    @Autowired
    private TestEnvironmentService testEnvironmentService;

    @Autowired
    private TestTypeService testTypeService;

    @Test
    public void whenFindAll_ThenAllPerformanceTestExecutionsAreReturned() {
        List<PerformanceTestExecution> performanceTestExecutionList = performanceTestExecutionService.list();
        assertFalse(performanceTestExecutionList.isEmpty());
    }

    @Test
    public void whenFindExistingPerformanceTestExecution_ThenExistingPerformanceTestExecutionIsReturned() {
        final String testSuiteName = "KPI Service Test";
        LocalDateTime startTime = LocalDateTime.parse("2022-06-15T21:10:03");
        PerformanceTest performanceTest = performanceTestService.findByName(testSuiteName);
        TestEnvironment testEnvironment = testEnvironmentService.findByName("Dev");
        PerformanceTestExecution performanceTestExecution = performanceTestExecutionService.find(startTime, performanceTest, testEnvironment);
        assertNotNull(performanceTestExecution);
        assertEquals(startTime, performanceTestExecution.getStartTime());
    }

}
