package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.PerformanceTest;
import ca.thoughtwire.readyapi.testresult.domain.model.PerformanceTestExecution;
import ca.thoughtwire.readyapi.testresult.domain.model.TestEnvironment;
import ca.thoughtwire.readyapi.testresult.domain.repository.PerformanceTestExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PerformanceTestExecutionService {

    @Autowired
    private PerformanceTestExecutionRepository performanceTestExecutionRepository;

    public List<PerformanceTestExecution> list() {
        return performanceTestExecutionRepository.findAll();
    }

    public PerformanceTestExecution find(LocalDateTime startTime, PerformanceTest performanceTest, TestEnvironment testEnvironment) {
        return performanceTestExecutionRepository.findByStartTimeAndPerformanceTestAndTestEnvironment(startTime, performanceTest, testEnvironment);
    }

    public PerformanceTestExecution findOrCreate(LocalDateTime startTime, PerformanceTest performanceTest, TestEnvironment testEnvironment) {
        PerformanceTestExecution performanceTestExecution = find(startTime, performanceTest, testEnvironment);
        if (performanceTestExecution == null) {
            performanceTestExecution = new PerformanceTestExecution(startTime, performanceTest, testEnvironment);
            performanceTestExecutionRepository.save(performanceTestExecution);
        }
        return performanceTestExecution;
    }


}