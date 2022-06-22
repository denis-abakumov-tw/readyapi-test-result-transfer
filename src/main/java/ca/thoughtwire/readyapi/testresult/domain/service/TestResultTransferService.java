package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.*;
import ca.thoughtwire.readyapi.testresult.domain.model.converter.EntityConverter;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.LoadUITestSteps;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.LoadUITestStepsItem;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.LoadUITestStepsHistoryItem;
import ca.thoughtwire.readyapi.testresult.domain.repository.TestCaseExecutionStatisticsRepository;
import ca.thoughtwire.readyapi.testresult.domain.repository.ScenarioExecutionRepository;
import ca.thoughtwire.readyapi.testresult.domain.repository.ScenarioRepository;
import ca.thoughtwire.readyapi.testresult.domain.repository.TestStepExecutionRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TestResultTransferService {

    public static final String TEST_TYPE_PERFORMANCE = "Performance";

    private static final String TEST_STEPS_HISTORY_XML = "LoadUITestStepsHistory.xml";

    private static final String TEST_STEPS_XML = "LoadUITestSteps.xml";

    @Autowired
    private TestTypeService testTypeService;

    @Autowired
    private PerformanceTestService performanceTestService;

    @Autowired
    private TestEnvironmentService testEnvironmentService;

    @Autowired
    private PerformanceTestExecutionService performanceTestExecutionService;

    @Autowired
    private ScenarioExecutionRepository scenarioExecutionRepository;

    @Autowired
    private ScenarioRepository scenarioRepository;

    @Autowired
    private TestCaseExecutionStatisticsRepository testCaseExecutionStatisticsRepository;

    @Autowired
    private TestStepExecutionRepository testStepExecutionRepository;

    private final XmlMapper mapper = new XmlMapper();

    private final EntityConverter converter = new EntityConverter();

    /**
     * Import performance test results from ReadyAPI LoadUITestStepsHistory.xml into the database.
     *
     * @param testEnvironmentName name of the machine where the tests were executed.
     * @param resultsFolder       folder with XML files exported from ReadyAPI.
     */
    public int importLoadUITestStepsHistory(String testEnvironmentName, Path resultsFolder) throws IOException {
        LocalDateTime startTime = getFileCreationTime(resultsFolder);
        String xml;
        int result = -1;

        xml = Files.readString(resultsFolder.resolve(TEST_STEPS_XML), StandardCharsets.UTF_8);
        LoadUITestSteps loadUITestSteps = mapper.readValue(xml, LoadUITestSteps.class);

        String performanceTestName = loadUITestSteps.getPerformanceTestName();
        TestType testType = testTypeService.findByNameOrCreate(TEST_TYPE_PERFORMANCE);
        PerformanceTest performanceTest = performanceTestService.findOrCreate(performanceTestName, testType);
        TestEnvironment testEnvironment = testEnvironmentService.findByNameOrCreate(testEnvironmentName);
        PerformanceTestExecution performanceTestExecution = performanceTestExecutionService.findOrCreate(startTime, performanceTest, testEnvironment);

        importLoadUITestSteps(startTime, performanceTestExecution, loadUITestSteps);

//        "LoadUITestStepsHistory.xml"
//        xml = Files.readString(resultsFolder.resolve(TEST_STEPS_HISTORY_XML), StandardCharsets.UTF_8);
//        LoadUITestStepsHistory loadUITestStepsHistory = mapper.readValue(xml, LoadUITestStepsHistory.class);
//        importLoadUITestStepsHistory(startTime, performanceTestName, "Marriott DEV", loadUITestStepsHistory);
//
//        for (LoadUITestStepsHistoryItem item : loadUITestStepsHistory) {
//            importScenarioMetrics(performanceTestExecution, item);
//            result++;
//        }
        return result;
    }

    private static LocalDateTime getFileCreationTime(Path path) throws IOException {
        FileTime fileTime = (FileTime) Files.getAttribute(path, "creationTime");
        return LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * Import performance test results from ReadyAPI LoadUITestSteps.xml into the database.
     *
     * @param startTime           time when the test execution was started.
     * @param loadUITestSteps     LoadUITestSteps.xml file exported from ReadyAPI.
     * @return
     */
    public int importLoadUITestSteps(LocalDateTime startTime, PerformanceTestExecution performanceTestExecution, LoadUITestSteps loadUITestSteps) {
        int result = -1;
        for (LoadUITestStepsItem item : loadUITestSteps) {
            importLoadUITestStepsItem(performanceTestExecution, item);
            result++;
        }
        return result;
    }

    private void importTestStepMetrics(TestCaseExecution testCaseExecution, LoadUITestStepsHistoryItem item) {
        if (item.getTestStepsMetrics() != null) {
//            String scenarioName = item.getTestCaseName();
//            Scenario scenario = scenarioRepository.findByName(scenarioName);
//            if (scenario == null) {
//                scenario = new Scenario(scenarioName);
//                scenario = scenarioRepository.save(scenario);
//            }
//            TestStepExecution testStepExecution = new TestStepExecution();
//            testStepExecution.setTestCaseExecution(testCaseExecution);
//            testStepExecution.setTestStep(testStep);
//            testStepExecutionRepository.save(testStepExecution);
//            List<TestStepExecutionMetrics> testStepExecutionMetrics = converter.convertLoadUITestStepsHistoryItem(item);
//            for (TestStepExecutionMetrics metrics : testStepExecutionMetrics) {
//                metrics.setTestStepExecution(testStepExecution);
//                testCaseExecutionStatisticsRepository.save(testCaseExecutionStatistics);
//            }
        }
    }

    private void importLoadUITestStepsItem(PerformanceTestExecution performanceTestExecution, LoadUITestStepsItem item) {
        if (item.getTestStatistics() != null) {
            String scenarioName = item.getTestCaseName();
            Scenario scenario = scenarioRepository.findByName(scenarioName);
//            if (scenario == null) {
//                scenario = new Scenario(scenarioName);
//                scenario.setPerformanceTest(performanceTestExecution.getPerformanceTest());
//                scenario = scenarioRepository.save(scenario);
//            }
//            ScenarioExecution scenarioExecution = new ScenarioExecution();
//            scenarioExecution.setPerformanceTestExecution(performanceTestExecution);
//            scenarioExecution.setScenario(scenario);
//            scenarioExecutionRepository.save(scenarioExecution);
//            List<TestCaseExecutionStatistics> testCaseExecutionStatisticsList = converter.convertLoadUITestStepsItem(item);
//            for (TestCaseExecutionStatistics testCaseExecutionStatistics : testCaseExecutionStatisticsList) {
//                testCaseExecutionStatistics.setScenarioExecution(scenarioExecution);
//                testCaseExecutionStatisticsRepository.save(testCaseExecutionStatistics);
//            }
        }
    }

}