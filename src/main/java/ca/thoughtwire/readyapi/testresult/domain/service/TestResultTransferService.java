package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.application.response.PerformanceTestImportResult;
import ca.thoughtwire.readyapi.testresult.application.response.ScenarioImportResult;
import ca.thoughtwire.readyapi.testresult.application.response.TestCaseImportResult;
import ca.thoughtwire.readyapi.testresult.application.response.TestStepImportResult;
import ca.thoughtwire.readyapi.testresult.domain.model.*;
import ca.thoughtwire.readyapi.testresult.domain.model.converter.EntityConverter;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.PerformanceResults;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.ScenarioWrapper;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.TestCaseWrapper;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.TestStepWrapper;
import ca.thoughtwire.readyapi.testresult.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public class TestResultTransferService {

    private final EntityConverter converter = new EntityConverter();

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
    private ScenarioService scenarioService;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestCaseExecutionRepository testCaseExecutionRepository;

    @Autowired
    private TestCaseExecutionMetricsRepository testCaseExecutionMetricsRepository;

    @Autowired
    private TestCaseExecutionStatisticsRepository testCaseExecutionStatisticsRepository;

    @Autowired
    private TestStepService testStepService;

    @Autowired
    private TestStepExecutionRepository testStepExecutionRepository;

    @Autowired
    private TestStepExecutionMetricsRepository testStepExecutionMetricsRepository;

    @Autowired
    private TestStepExecutionStatisticsRepository testStepExecutionStatisticsRepository;

    /**
     * Import performance test results from ReadyAPI XMLs into the database.
     *
     * @param testEnvironmentName name of the machine where the tests were executed.
     * @param resultsFolder       folder with XML files exported from ReadyAPI.
     */
    public PerformanceTestImportResult importPerformanceResults(String testEnvironmentName, Path resultsFolder) throws IOException {
        TestType testType = testTypeService.findByNameOrCreate(PerformanceResults.TEST_TYPE);
        PerformanceResults performanceResults = new PerformanceResults();
        performanceResults.collect(resultsFolder);
        String performanceTestName = performanceResults.getPerformanceTestName();
        PerformanceTestImportResult importResult = new PerformanceTestImportResult(performanceTestName);
        importResult.setEnvironment(testEnvironmentName);
        importResult.setCreated(performanceResults.getStartTime());
        PerformanceTest performanceTest = performanceTestService.findOrCreate(performanceTestName, testType);
        TestEnvironment testEnvironment = testEnvironmentService.findByNameOrCreate(testEnvironmentName);
        PerformanceTestExecution performanceTestExecution = performanceTestExecutionService.find(performanceResults.getStartTime(), performanceTest, testEnvironment);
        if (performanceTestExecution == null) {
            performanceTestExecution = performanceTestExecutionService.create(performanceResults.getStartTime(), performanceTest, testEnvironment);
            for (ScenarioWrapper scenarioWrapper : performanceResults.getScenarios().values()) {
                ScenarioImportResult scenarioImportResult = importScenario(performanceTestExecution, scenarioWrapper);
                importResult.getScenarios().add(scenarioImportResult);
            }
            importResult.setStatus(PerformanceTestImportResult.SUCCESSFULLY_IMPORTED);
        } else {
            importResult.setStatus(PerformanceTestImportResult.ALREADY_EXISTS);
        }
        return importResult;
    }

    private ScenarioImportResult importScenario(PerformanceTestExecution performanceTestExecution, ScenarioWrapper scenarioWrapper) {
        ScenarioImportResult scenarioImportResult = new ScenarioImportResult(scenarioWrapper.getName());
        Scenario scenario = scenarioService.findOrCreate(scenarioWrapper.getName());
        ScenarioExecution scenarioExecution = new ScenarioExecution(performanceTestExecution, scenario);
        scenarioExecutionRepository.save(scenarioExecution);
        for (TestCaseWrapper testCaseWrapper : scenarioWrapper.getTestCases().values()) {
            TestCaseImportResult testCaseImportResult = importTestCase(scenarioExecution, testCaseWrapper);
            scenarioImportResult.getTestCases().add(testCaseImportResult);
        }
        return scenarioImportResult;
    }

    private TestCaseImportResult importTestCase(ScenarioExecution scenarioExecution, TestCaseWrapper testCaseWrapper) {
        TestCaseImportResult testCaseImportResult = new TestCaseImportResult(testCaseWrapper.getName());
        TestCase testCase = testCaseService.findOrCreate(testCaseWrapper.getName());
        TestCaseExecution testCaseExecution = new TestCaseExecution(scenarioExecution, testCase);
        testCaseExecutionRepository.save(testCaseExecution);
        List<TestCaseExecutionMetrics> testCaseExecutionMetrics = converter.toTestCaseExecutionMetrics(testCaseWrapper.getMetrics());
        for (TestCaseExecutionMetrics metrics : testCaseExecutionMetrics) {
            metrics.setTestCaseExecution(testCaseExecution);
            testCaseExecutionMetricsRepository.save(metrics);
        }
        testCaseImportResult.setMetrics(testCaseExecutionMetrics.size());
        TestCaseExecutionStatistics testCaseExecutionStatistics = converter.toTestCaseExecutionStatistics(testCaseWrapper.getStatistics());
        testCaseExecutionStatistics.setTestCaseExecution(testCaseExecution);
        testCaseExecutionStatisticsRepository.save(testCaseExecutionStatistics);
        testCaseImportResult.setStatisticsImported(true);
        for (TestStepWrapper testStepWrapper : testCaseWrapper.getTestSteps().values()) {
            TestStepImportResult testStepImportResult = importTestStep(testCaseExecution, testStepWrapper);
            testCaseImportResult.getTestSteps().add(testStepImportResult);
        }
        return testCaseImportResult;
    }

    private TestStepImportResult importTestStep(TestCaseExecution testCaseExecution, TestStepWrapper testStepWrapper) {
        TestStepImportResult testStepImportResult = new TestStepImportResult(testStepWrapper.getName());
        TestStep testStep = testStepService.findOrCreate(testStepWrapper.getName());
        TestStepExecution testStepExecution = new TestStepExecution(testCaseExecution, testStep);
        testStepExecutionRepository.save(testStepExecution);
        List<TestStepExecutionMetrics> testStepExecutionMetrics = converter.toTestStepExecutionMetrics(testStepWrapper.getMetrics());
        for (TestStepExecutionMetrics metrics : testStepExecutionMetrics) {
            metrics.setTestStepExecution(testStepExecution);
            testStepExecutionMetricsRepository.save(metrics);
        }
        testStepImportResult.setMetrics(testStepExecutionMetrics.size());
        TestStepExecutionStatistics testStepExecutionStatistics = converter.toTestStepExecutionStatistics(testStepWrapper.getStatistics());
        testStepExecutionStatistics.setTestStepExecution(testStepExecution);
        testStepExecutionStatisticsRepository.save(testStepExecutionStatistics);
        testStepImportResult.setStatisticsImported(true);
        return testStepImportResult;
    }

}