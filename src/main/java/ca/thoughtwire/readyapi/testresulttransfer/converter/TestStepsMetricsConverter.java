package ca.thoughtwire.readyapi.testresulttransfer.converter;

import ca.thoughtwire.readyapi.testresulttransfer.models.PerformanceResult;
import ca.thoughtwire.readyapi.testresulttransfer.xml.loaduiteststepshistory.LoadUITestStepsHistoryItem;
import ca.thoughtwire.readyapi.testresulttransfer.xml.loaduiteststepshistory.TestStepsMetrics;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TestStepsMetricsConverter {

    public List<PerformanceResult> convertToPerformanceResults(LoadUITestStepsHistoryItem item) {
        List<PerformanceResult> results = new ArrayList<>();
        BigInteger lastErrCount = BigInteger.ZERO;
        for (TestStepsMetrics metrics : item) {
            PerformanceResult performanceResult = convertToPerformanceResult(metrics, lastErrCount);
            results.add(performanceResult);
            lastErrCount = BigInteger.valueOf(metrics.getErr());
        }
        return results;
    }

    protected static PerformanceResult convertToPerformanceResult(TestStepsMetrics testStepsMetrics, BigInteger lastErrCount) {
        PerformanceResult performanceResult = new PerformanceResult();
        performanceResult.setSecondsFromStart(BigInteger.valueOf(testStepsMetrics.getTimeSec()));
        performanceResult.setMinRequestTimeMs(BigInteger.valueOf(testStepsMetrics.getMinMs()));
        performanceResult.setMaxRequestTimeMs(BigInteger.valueOf(testStepsMetrics.getMaxMs()));
        performanceResult.setMedianRequestTimeMs(BigInteger.valueOf(testStepsMetrics.getMedianMs()));
        performanceResult.setLastRequestTimeMs(BigInteger.valueOf(testStepsMetrics.getLastMs()));
        performanceResult.setRequestCount(BigInteger.valueOf(testStepsMetrics.getCount()));
        performanceResult.setTransactionsPerSecond(BigInteger.valueOf(testStepsMetrics.getTps()));
        BigInteger errorsPerSecond = BigInteger.valueOf(testStepsMetrics.getErr()).subtract(lastErrCount);
        performanceResult.setErrorsPerSecond(errorsPerSecond);
        return performanceResult;
    }

}
