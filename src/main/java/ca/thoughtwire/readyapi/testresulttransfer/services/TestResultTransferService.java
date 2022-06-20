package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.converter.TestStepsMetricsConverter;
import ca.thoughtwire.readyapi.testresulttransfer.models.*;
import ca.thoughtwire.readyapi.testresulttransfer.repositories.PerformanceResultRepository;
import ca.thoughtwire.readyapi.testresulttransfer.repositories.TestCaseExecutionRepository;
import ca.thoughtwire.readyapi.testresulttransfer.repositories.TestCaseRepository;
import ca.thoughtwire.readyapi.testresulttransfer.xml.loaduiteststepshistory.LoadUITestStepsHistory;
import ca.thoughtwire.readyapi.testresulttransfer.xml.loaduiteststepshistory.LoadUITestStepsHistoryItem;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestResultTransferService {

    public static final String TEST_TYPE_NAME = "Performance";

    private final XmlMapper mapper = new XmlMapper();

    @Autowired
    private TestTypeService testTypeService;

    @Autowired
    private TestSuiteService testSuiteService;

    @Autowired
    private TestEnvironmentService testEnvironmentService;

    @Autowired
    private TestSuiteExecutionService testSuiteExecutionService;

    @Autowired
    private TestCaseExecutionRepository testCaseExecutionRepository;

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private PerformanceResultRepository performanceResultRepository;

    private final TestStepsMetricsConverter converter = new TestStepsMetricsConverter();

    /**
     * Import performance test results from ReadyAPI LoadUITestStepsHistory.xml into the database.
     *
     * @param startTime                 time when the test execution was started.
     * @param testSuiteName             test suite name (e.g.: User Service Load Tests).
     * @param testEnvironmentName       name of the machine where the tests were executed.
     * @param loadUITestStepsHistoryXml LoadUITestStepsHistory.xml file exported from ReadyAPI.
     */
    public void importLoadUITestStepsHistory(LocalDateTime startTime, String testSuiteName, String testEnvironmentName, String loadUITestStepsHistoryXml) {
        TestType testType = testTypeService.findByNameOrCreate(TEST_TYPE_NAME);
        TestSuite testSuite = testSuiteService.findOrCreate(testSuiteName, testType);
        TestEnvironment testEnvironment = testEnvironmentService.findByNameOrCreate(testEnvironmentName);
        TestSuiteExecution testSuiteExecution = testSuiteExecutionService.findOrCreate(startTime, testSuite, testEnvironment);
        try {
            LoadUITestStepsHistory loadUITestStepsHistory = mapper.readValue(loadUITestStepsHistoryXml, LoadUITestStepsHistory.class);
            for (LoadUITestStepsHistoryItem item : loadUITestStepsHistory) {
                importTestCaseExecution(testSuiteExecution, item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Import performance test results from ReadyAPI LoadUITestSteps.xml into the database.
     *
     * @param startTime           time when the test execution was started.
     * @param testSuiteName       test suite name (e.g.: User Service Load Tests).
     * @param testEnvironmentName name of the machine where the tests were executed.
     * @param loadUITestStepsXml  LoadUITestSteps.xml file exported from ReadyAPI.
     */
//    public void importLoadUITestSteps(LocalDateTime startTime, String testSuiteName, String testEnvironmentName, String loadUITestStepsXml) {
//        final String testTypeName = "Performance";
//        TestType testType = testEnvironmentService.findByName(testTypeName);
//        if (testType == null) {
//            testType = testEnvironmentService.save(new TestType(testTypeName));
//        }
//        TestSuite testSuite = testSuiteRepository.findByName(testSuiteName);
//        if (testSuite == null) {
//            testSuite = new TestSuite(testSuiteName);
//            testSuite.setTestType(testType);
//            testSuite = testSuiteRepository.save(testSuite);
//        }
//        TestEnvironment testEnvironment = environmentRepository.findByName(testEnvironmentName);
//        if (testEnvironment == null) {
//            testEnvironment = environmentRepository.save(new TestEnvironment(testEnvironmentName));
//        }
//        TestSuiteExecution testSuiteExecution = new TestSuiteExecution();
//        testSuiteExecution.setStartTime(startTime);
//        testSuiteExecution.setTestSuite(testSuite);
//        testSuiteExecution.setTestEnvironment(testEnvironment);
//        testSuiteExecutionRepository.save(testSuiteExecution);
//        try {
//            LoadUITestStepsHistory loadUITestStepsHistory = mapper.readValue(loadUITestStepsXml, LoadUITestStepsHistory.class);
//            for (LoadUITestStepsHistoryItem item : loadUITestStepsHistory) {
//                importTestCaseExecution(testSuiteExecution, item);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    private void importTestCaseExecution(TestSuiteExecution testSuiteExecution, LoadUITestStepsHistoryItem item) {
        if (item.getTestStepsMetrics() != null) {
            String testCaseName = item.getTestStepName();
            TestCase testCase = testCaseRepository.findByName(testCaseName);
            if (testCase == null) {
                testCase = new TestCase(testCaseName);
                testCase.setTestSuite(testSuiteExecution.getTestSuite());
                testCase = testCaseRepository.save(testCase);
            }
            TestCaseExecution testCaseExecution = new TestCaseExecution();
            testCaseExecution.setTestSuiteExecution(testSuiteExecution);
            testCaseExecution.setTestCase(testCase);
            testCaseExecutionRepository.save(testCaseExecution);
            List<PerformanceResult> performanceResultList = converter.convertToPerformanceResults(item);
            for (PerformanceResult performanceResult : performanceResultList) {
                performanceResult.setTestCaseExecution(testCaseExecution);
                performanceResultRepository.save(performanceResult);
            }
        }
    }

}