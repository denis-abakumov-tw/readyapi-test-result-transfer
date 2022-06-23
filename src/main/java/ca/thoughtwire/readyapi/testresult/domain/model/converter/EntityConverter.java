package ca.thoughtwire.readyapi.testresult.domain.model.converter;

import ca.thoughtwire.readyapi.testresult.domain.model.TestCaseExecutionMetrics;
import ca.thoughtwire.readyapi.testresult.domain.model.TestCaseExecutionStatistics;
import ca.thoughtwire.readyapi.testresult.domain.model.TestStepExecutionMetrics;
import ca.thoughtwire.readyapi.testresult.domain.model.TestStepExecutionStatistics;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.TestStatistics;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.PerformanceMetrics;

import java.util.ArrayList;
import java.util.List;

public class EntityConverter {

    public List<TestCaseExecutionMetrics> toTestCaseExecutionMetrics(List<PerformanceMetrics> item) {
        List<TestCaseExecutionMetrics> results = new ArrayList<>();
        int lastErrCount = 0;
        for (PerformanceMetrics metrics : item) {
            TestCaseExecutionMetrics testCaseExecutionMetrics = new TestCaseExecutionMetrics();
            testCaseExecutionMetrics.setSecondsFromStart(metrics.getTimeSec());
            testCaseExecutionMetrics.setMinRequestTimeMs(metrics.getMinMs());
            testCaseExecutionMetrics.setMaxRequestTimeMs(metrics.getMaxMs());
            testCaseExecutionMetrics.setMedianRequestTimeMs(metrics.getMedianMs());
            testCaseExecutionMetrics.setLastRequestTimeMs(metrics.getLastMs());
            testCaseExecutionMetrics.setRequestCount(metrics.getCount());
            testCaseExecutionMetrics.setTransactionsPerSecond(metrics.getTps());
            testCaseExecutionMetrics.setErrorsPerSecond(metrics.getErr() - lastErrCount);
            results.add(testCaseExecutionMetrics);
            lastErrCount = metrics.getErr();
        }
        return results;
    }

    public TestCaseExecutionStatistics toTestCaseExecutionStatistics(TestStatistics testStatistics) {
        TestCaseExecutionStatistics testCaseExecutionStatistics = new TestCaseExecutionStatistics();
        testCaseExecutionStatistics.setCnt(testStatistics.getCnt());
        testCaseExecutionStatistics.setMin(testStatistics.getMin());
        testCaseExecutionStatistics.setMax(testStatistics.getMax());
        testCaseExecutionStatistics.setAvg(testStatistics.getAvg());
        testCaseExecutionStatistics.setStdDev(testStatistics.getStdDev());
        testCaseExecutionStatistics.setMinAvg(testStatistics.getMinAvg());
        testCaseExecutionStatistics.setMaxAvg(testStatistics.getMaxAvg());
        testCaseExecutionStatistics.setFailure(testStatistics.getFailure());
        testCaseExecutionStatistics.setFailureRatio(testStatistics.getFailureRatio());
        testCaseExecutionStatistics.setPercentile75(testStatistics.getPercentile75());
        testCaseExecutionStatistics.setPercentile90(testStatistics.getPercentile90());
        testCaseExecutionStatistics.setPercentile95(testStatistics.getPercentile95());
        testCaseExecutionStatistics.setPercentile98(testStatistics.getPercentile98());
        testCaseExecutionStatistics.setMedian(testStatistics.getMedian());
        testCaseExecutionStatistics.setLast(testStatistics.getLast());
        testCaseExecutionStatistics.setTps(testStatistics.getTps());
        return testCaseExecutionStatistics;
    }

    public List<TestStepExecutionMetrics> toTestStepExecutionMetrics(List<PerformanceMetrics> item) {
        List<TestStepExecutionMetrics> results = new ArrayList<>();
        int lastErrCount = 0;
        for (PerformanceMetrics metrics : item) {
            TestStepExecutionMetrics testStepExecutionMetrics = new TestStepExecutionMetrics();
            testStepExecutionMetrics.setSecondsFromStart(metrics.getTimeSec());
            testStepExecutionMetrics.setMinRequestTimeMs(metrics.getMinMs());
            testStepExecutionMetrics.setMaxRequestTimeMs(metrics.getMaxMs());
            testStepExecutionMetrics.setMedianRequestTimeMs(metrics.getMedianMs());
            testStepExecutionMetrics.setLastRequestTimeMs(metrics.getLastMs());
            testStepExecutionMetrics.setRequestCount(metrics.getCount());
            testStepExecutionMetrics.setTransactionsPerSecond(metrics.getTps());
            testStepExecutionMetrics.setErrorsPerSecond(metrics.getErr() - lastErrCount);
            results.add(testStepExecutionMetrics);
            lastErrCount = metrics.getErr();
        }
        return results;
    }

    public TestStepExecutionStatistics toTestStepExecutionStatistics(TestStatistics testStatistics) {
        TestStepExecutionStatistics testStepExecutionStatistics = new TestStepExecutionStatistics();
        testStepExecutionStatistics.setCnt(testStatistics.getCnt());
        testStepExecutionStatistics.setMin(testStatistics.getMin());
        testStepExecutionStatistics.setMax(testStatistics.getMax());
        testStepExecutionStatistics.setAvg(testStatistics.getAvg());
        testStepExecutionStatistics.setFailure(testStatistics.getFailure());
        testStepExecutionStatistics.setFailureRatio(testStatistics.getFailureRatio());
        testStepExecutionStatistics.setPercentile75(testStatistics.getPercentile75());
        testStepExecutionStatistics.setPercentile90(testStatistics.getPercentile90());
        testStepExecutionStatistics.setPercentile95(testStatistics.getPercentile95());
        testStepExecutionStatistics.setPercentile98(testStatistics.getPercentile98());
        testStepExecutionStatistics.setMedian(testStatistics.getMedian());
        testStepExecutionStatistics.setLast(testStatistics.getLast());
        testStepExecutionStatistics.setTps(testStatistics.getTps());
        return testStepExecutionStatistics;
    }

}
