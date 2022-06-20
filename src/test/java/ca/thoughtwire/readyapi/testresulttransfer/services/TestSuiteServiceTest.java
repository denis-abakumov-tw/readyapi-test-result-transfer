package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestSuite;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSuiteServiceTest {

    @Autowired
    private TestSuiteService testSuiteService;

    @Autowired
    private TestTypeService testTypeService;

    @Test
    public void whenFindAll_ThenAllTestSuitesAreReturned() {
        List<TestSuite> testSuiteList = testSuiteService.list();
        assertFalse(testSuiteList.isEmpty());
    }

    @Test
    public void whenFindOrCreateExistingTestSuite_ThenExistingTestSuiteIsReturned() {
        final String testSuiteName = "KPI Service Test";
        TestSuite testSuite = testSuiteService.findByName(testSuiteName);
        assertNotNull(testSuite);
        testSuite = testSuiteService.findOrCreate(testSuiteName, null);
        assertNotNull(testSuite);
        assertEquals(testSuiteName, testSuite.getName());
    }

    @Test
    public void whenFindOrCreateNonExistingTestSuite_ThenNewTestSuiteIsReturned() {
        final String testSuiteName = "New Service Test";
        TestSuite testSuite = testSuiteService.findByName(testSuiteName);
        assertNull(testSuite);
        TestType testType = testTypeService.findByNameOrCreate("Performance");
        testSuite = testSuiteService.findOrCreate(testSuiteName, testType);
        assertNotNull(testSuite);
        assertEquals(testSuiteName, testSuite.getName());
    }

}
