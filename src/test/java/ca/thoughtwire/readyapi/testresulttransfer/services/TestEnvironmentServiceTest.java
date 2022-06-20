package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestEnvironment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEnvironmentServiceTest {

    @Autowired
    private TestEnvironmentService testEnvironmentService;

    @Test
    public void whenFindAll_ThenAllTestEnvironmentsAreReturned() {
        List<TestEnvironment> testEnvironmentList = testEnvironmentService.list();
        assertFalse(testEnvironmentList.isEmpty());
    }

    @Test
    public void whenFindOrCreateExistingTestEnvironment_ThenExistingTestEnvironmentIsReturned() {
        final String testEnvironmentName = "Dev";
        TestEnvironment testEnvironment = testEnvironmentService.findByName(testEnvironmentName);
        assertNotNull(testEnvironment);
        testEnvironment = testEnvironmentService.findByNameOrCreate(testEnvironmentName);
        assertNotNull(testEnvironment);
        assertEquals(testEnvironmentName, testEnvironment.getName());
    }

    @Test
    public void whenFindOrCreateNonExistingTestEnvironment_ThenNewTestEnvironmentIsReturned() {
        final String testEnvironmentName = "New Test Environment";
        TestEnvironment testEnvironment = testEnvironmentService.findByName(testEnvironmentName);
        assertNull(testEnvironment);
        testEnvironment = testEnvironmentService.findByNameOrCreate(testEnvironmentName);
        assertNotNull(testEnvironment);
        assertEquals(testEnvironmentName, testEnvironment.getName());
    }

}
