package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.PerformanceResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceResultServiceTest {

    @Autowired
    private PerformanceResultService performanceResultService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<PerformanceResult> performanceResults = performanceResultService.get(1L, 2L);
        Assert.assertEquals(10, performanceResults.size());
    }

    @Test
    public void whenGetResult_thenParentFieldsArePopulated() {
        List<PerformanceResult> performanceResults = performanceResultService.get(1L, 2L);
        Assert.assertNotNull(performanceResults.get(0).getTestExecution());
        Assert.assertNotNull(performanceResults.get(0).getPerformanceMetric());
    }

    @Test
    public void whenAddNewResult_thenHibernateReturnsResult() {
//        PerformanceResult performanceResult = performanceResultService.add("New Result");
//        Assert.assertNotNull(performanceResult);
    }

}
