package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.PerformanceTest;
import ca.thoughtwire.readyapi.testresult.domain.model.TestType;
import ca.thoughtwire.readyapi.testresult.domain.repository.PerformanceTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceTestService {

    @Autowired
    private PerformanceTestRepository performanceTestRepository;

    public List<PerformanceTest> list() {
        return performanceTestRepository.findAll();
    }

    public PerformanceTest findOrCreate(String performanceTestName, TestType testType) {
        PerformanceTest performanceTest = performanceTestRepository.findByName(performanceTestName);
        if (performanceTest == null) {
            performanceTest = new PerformanceTest(performanceTestName);
            performanceTest.setTestType(testType);
            performanceTestRepository.save(performanceTest);
        }
        return performanceTest;
    }

    public PerformanceTest findByName(String performanceTestName) {
        return performanceTestRepository.findByName(performanceTestName);
    }

}