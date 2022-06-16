package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.PerformanceMetric;
import ca.thoughtwire.readyapi.testresulttransfer.repositories.PerformanceMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceMetricService {

    @Autowired
    private PerformanceMetricRepository performanceMetricRepository;

    public List<PerformanceMetric> list() {
        return performanceMetricRepository.findAll();
    }

    public PerformanceMetric add(String name) {
        if (performanceMetricRepository.existsByName(name)) {
            return performanceMetricRepository.findByName(name).get();
        } else {
            PerformanceMetric performanceMetric = new PerformanceMetric();
            performanceMetric.setName(name);
            return performanceMetricRepository.save(performanceMetric);
        }
    }

}
