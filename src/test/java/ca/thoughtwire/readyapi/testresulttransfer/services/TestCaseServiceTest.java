package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestCase;
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
public class TestCaseServiceTest {

    @Autowired
    private TestCaseService testCaseService;

    @Test
    public void whenGetAllTestCases_thenTestCasesAreReturned() {
        List<TestCase> testCases = testCaseService.list();
        Assert.assertNotNull(testCases);
        Assert.assertFalse(testCases.isEmpty());
        Assert.assertNotNull(testCases.get(0));
    }

}
