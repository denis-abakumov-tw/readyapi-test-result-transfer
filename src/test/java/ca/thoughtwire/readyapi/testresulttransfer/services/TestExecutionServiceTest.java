package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestExecution;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExecutionServiceTest {

    @Autowired
    private TestExecutionService testExecutionService;

    @Test
    public void whenGetAllTestExecutions_thenTestExecutionsAreReturned() {
        List<TestExecution> testExecution = testExecutionService.list();
        Assert.assertNotNull(testExecution);
        Assert.assertFalse(testExecution.isEmpty());
        Assert.assertNotNull(testExecution.get(0));
    }

    @Test
    public void whenAddNewExecution_thenParentEntitiesAreCreated() {
        LocalDateTime started = LocalDateTime.now();
        String machine = "TestVM";
        String test = "KPI Service Test";
        String testCaseType = "Performance";
        TestExecution testExecution = testExecutionService.add(started, machine, testCaseType, test);
        Assert.assertNotNull(testExecution);
        Assert.assertNotNull(testExecution.getTestCase());
        Assert.assertNotNull(testExecution.getTestStation());
    }

}
