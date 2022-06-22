package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.PerformanceTest;
import ca.thoughtwire.readyapi.testresult.domain.model.TestType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceTestServiceTest {

    @Autowired
    private PerformanceTestService performanceTestService;

    @Autowired
    private TestTypeService testTypeService;

    @Test
    public void whenFindAll_ThenAllPerformanceTestsAreReturned() {
        List<PerformanceTest> performanceTestList = performanceTestService.list();
        assertFalse(performanceTestList.isEmpty());
    }

    @Test
    public void whenFindOrCreateExistingPerformanceTest_ThenExistingPerformanceTestIsReturned() {
        final String testSuiteName = "KPI Service Test";
        PerformanceTest performanceTest = performanceTestService.findByName(testSuiteName);
        assertNotNull(performanceTest);
        performanceTest = performanceTestService.findOrCreate(testSuiteName, null);
        assertNotNull(performanceTest);
        assertEquals(testSuiteName, performanceTest.getName());
    }

    @Test
    public void whenFindOrCreateNonExistingPerformanceTest_ThenNewPerformanceTestIsReturned() {
        final String testSuiteName = "New Performance Test";
        PerformanceTest performanceTest = performanceTestService.findByName(testSuiteName);
        assertNull(performanceTest);
        TestType testType = testTypeService.findByNameOrCreate("Performance");
        performanceTest = performanceTestService.findOrCreate(testSuiteName, testType);
        assertNotNull(performanceTest);
        assertEquals(testSuiteName, performanceTest.getName());
    }

}
