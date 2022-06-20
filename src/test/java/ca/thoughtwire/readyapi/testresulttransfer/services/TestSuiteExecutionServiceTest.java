package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestEnvironment;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestSuite;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestSuiteExecution;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestType;
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
public class TestSuiteExecutionServiceTest {

    @Autowired
    private TestSuiteExecutionService testSuiteExecutionService;

    @Autowired
    private TestSuiteService testSuiteService;

    @Autowired
    private TestEnvironmentService testEnvironmentService;

    @Autowired
    private TestTypeService testTypeService;

    @Test
    public void whenFindAll_ThenAllTestSuiteExecutionsAreReturned() {
        List<TestSuiteExecution> testSuiteExecutionList = testSuiteExecutionService.list();
        assertFalse(testSuiteExecutionList.isEmpty());
    }

    @Test
    public void whenFindOrCreateExistingTestSuiteExecution_ThenExistingTestSuiteExecutionIsReturned() {
        final String testSuiteName = "KPI Service Test";
        LocalDateTime startTime = LocalDateTime.parse("2022-06-15T21:10:03");
        TestSuite testSuite = testSuiteService.findByName(testSuiteName);
        TestEnvironment testEnvironment = testEnvironmentService.findByName("Dev");
        TestSuiteExecution testSuiteExecution = testSuiteExecutionService.find(startTime, testSuite, testEnvironment);
        assertNotNull(testSuiteExecution);
        testSuiteExecution = testSuiteExecutionService.findOrCreate(startTime, testSuite, testEnvironment);
        assertNotNull(testSuiteExecution);
        assertEquals(startTime, testSuiteExecution.getStartTime());
    }

    @Test
    public void whenFindOrCreateNonExistingTestSuiteExecution_ThenNewTestSuiteExecutionIsReturned() {
        final String testSuiteName = "New Test Suite Execution";
        LocalDateTime startTime = LocalDateTime.parse("2022-06-15T21:10:03");
        final String testTypeName = "Performance";
        TestType testType = testTypeService.findByName(testTypeName);
        TestSuite testSuite = testSuiteService.findOrCreate(testSuiteName, testType);
        TestEnvironment testEnvironment = testEnvironmentService.findByName("Dev");
        TestSuiteExecution testSuiteExecution = testSuiteExecutionService.find(startTime, testSuite, testEnvironment);
        assertNull(testSuiteExecution);
        testSuiteExecution = testSuiteExecutionService.findOrCreate(startTime, testSuite, testEnvironment);
        assertNotNull(testSuiteExecution);
        assertEquals(startTime, testSuiteExecution.getStartTime());
    }

}
