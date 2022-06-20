package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestEnvironment;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestSuite;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestSuiteExecution;
import ca.thoughtwire.readyapi.testresulttransfer.repositories.TestSuiteExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestSuiteExecutionService {

    @Autowired
    private TestSuiteExecutionRepository testSuiteExecutionRepository;

    public List<TestSuiteExecution> list() {
        return testSuiteExecutionRepository.findAll();
    }

    public TestSuiteExecution find(LocalDateTime startTime, TestSuite testSuite, TestEnvironment testEnvironment) {
        return testSuiteExecutionRepository.findByStartTimeAndTestSuiteAndTestEnvironment(startTime, testSuite, testEnvironment);
    }

    public TestSuiteExecution findOrCreate(LocalDateTime startTime, TestSuite testSuite, TestEnvironment testEnvironment) {
        TestSuiteExecution testSuiteExecution = find(startTime, testSuite, testEnvironment);
        if (testSuiteExecution == null) {
            testSuiteExecution = new TestSuiteExecution();
            testSuiteExecution.setStartTime(startTime);
            testSuiteExecution.setTestSuite(testSuite);
            testSuiteExecution.setTestEnvironment(testEnvironment);
            testSuiteExecutionRepository.save(testSuiteExecution);
        }
        return testSuiteExecution;
    }


}