package ca.thoughtwire.readyapi.testresulttransfer.services;

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
        for (File file : new File("src/test/resources/").listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"))) {
            FileTime creationTime = (FileTime) Files.getAttribute(file.toPath(), "creationTime");
            String xml = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            String testSuiteName = file.getName();
            testSuiteName = testSuiteName.contains("_") ? testSuiteName.split("_")[0] : testSuiteName.split("\\.")[0];
            testResultTransferService.importLoadUITestStepsHistory(LocalDateTime.ofInstant(creationTime.toInstant(), ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS),
                    testSuiteName, "Marriott DEV", xml);
        }
    }

    @Test
    public void whenAddNewResult_thenHibernateReturnsResult() {
//        PerformanceResult performanceResult = performanceResultService.add("New Result");
//        Assert.assertNotNull(performanceResult);
    }

}
