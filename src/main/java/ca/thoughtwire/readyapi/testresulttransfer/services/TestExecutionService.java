package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestCase;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestExecution;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestStation;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestType;
import ca.thoughtwire.readyapi.testresulttransfer.repositories.PerformanceExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestExecutionService {

    @Autowired
    private PerformanceExecutionRepository performanceExecutionRepository;

    public List<TestExecution> list() {
        return performanceExecutionRepository.findAll();
    }

    public TestExecution add(LocalDateTime started, String testStationName, String testCaseType, String testCaseName) {
        TestExecution testExecution = new TestExecution();
        testExecution.setStarted(started);
        testExecution.setTestStation(new TestStation(testStationName));
        testExecution.setTestCase(new TestCase(testCaseName, new TestType(testCaseType)));
        return performanceExecutionRepository.save(testExecution);
    }

}
