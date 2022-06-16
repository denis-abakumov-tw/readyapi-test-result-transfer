package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.PerformanceMetric;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceMetricServiceTest {

    @Autowired
    private PerformanceMetricService performanceMetricService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<PerformanceMetric> performanceMetrics = performanceMetricService.list();
         Assert.assertEquals(6, performanceMetrics.size());
    }

    @Test
    public void whenAddNewMetric_thenHibernateReturnsMetric() {
//        PerformanceMetric performanceMetric = performanceMetricService.add("New Metric");
//        Assert.assertNotNull(performanceMetric);
    }

}
