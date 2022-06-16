package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.PerformanceMetric;
import ca.thoughtwire.readyapi.testresulttransfer.models.PerformanceResult;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestExecution;
import ca.thoughtwire.readyapi.testresulttransfer.repositories.PerformanceResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PerformanceResultService {

    @Autowired
    private PerformanceResultRepository performanceResultRepository;

    public List<PerformanceResult> get(Long executionId, Long metricId) {
        return performanceResultRepository.findAllByTestExecution_IdAndPerformanceMetric_Id(executionId, metricId);
    }

    public void add(Long executionId, Long metricId, Long seconds, BigDecimal amount) {
        PerformanceResult performanceResult = new PerformanceResult();
        performanceResult.setTestExecution(new TestExecution(executionId));
        performanceResult.setPerformanceMetric(new PerformanceMetric(metricId));
        performanceResult.setSeconds(seconds);
        performanceResult.setAmount(amount);
        performanceResultRepository.save(performanceResult);
    }

}
