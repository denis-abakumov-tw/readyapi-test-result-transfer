package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.LoadUITestSteps;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.LoadUITestStepsHistory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestResultTransferServiceTest {

    @Autowired
    private TestResultTransferService testResultTransferService;


    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() throws Exception {
        File resources = new File("src/test/resources/");
        String[] dirs = resources.list((file, name) -> file.isDirectory());
        if (dirs == null)
            return;
        for (String dirName : dirs) {
            File directory = new File(resources, dirName);

        }
    }

    @Test
    public void whenAddNewResult_thenHibernateReturnsResult() {
//        TestCaseExecutionStatistics testCaseExecutionResult = testCaseExecutionResultService.add("New Result");
//        Assert.assertNotNull(testCaseExecutionResult);
    }

}
