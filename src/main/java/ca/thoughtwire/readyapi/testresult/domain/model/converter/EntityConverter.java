package ca.thoughtwire.readyapi.testresult.domain.model.converter;

import ca.thoughtwire.readyapi.testresult.domain.model.TestStepExecutionMetrics;
import ca.thoughtwire.readyapi.testresult.domain.model.TestStepExecutionStatistics;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.LoadUITestStepsItem;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.TestStatistics;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.LoadUITestStepsHistoryItem;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.TestStepsMetrics;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EntityConverter {

    public List<TestStepExecutionMetrics> convertLoadUITestStepsHistoryItem(LoadUITestStepsHistoryItem item) {
        List<TestStepExecutionMetrics> results = new ArrayList<>();
        BigInteger lastErrCount = BigInteger.ZERO;
        for (TestStepsMetrics metrics : item) {
            TestStepExecutionMetrics testStepExecutionMetrics = convertTestStepsMetrics(metrics, lastErrCount);
            results.add(testStepExecutionMetrics);
            lastErrCount = BigInteger.valueOf(metrics.getErr());
        }
        return results;
    }

    protected static TestStepExecutionMetrics convertTestStepsMetrics(TestStepsMetrics testStepsMetrics, BigInteger lastErrCount) {
        TestStepExecutionMetrics testStepExecutionStatistics = new TestStepExecutionMetrics();
        testStepExecutionStatistics.setSecondsFromStart(BigInteger.valueOf(testStepsMetrics.getTimeSec()));
        testStepExecutionStatistics.setMinRequestTimeMs(BigInteger.valueOf(testStepsMetrics.getMinMs()));
        testStepExecutionStatistics.setMaxRequestTimeMs(BigInteger.valueOf(testStepsMetrics.getMaxMs()));
        testStepExecutionStatistics.setMedianRequestTimeMs(BigInteger.valueOf(testStepsMetrics.getMedianMs()));
        testStepExecutionStatistics.setLastRequestTimeMs(BigInteger.valueOf(testStepsMetrics.getLastMs()));
        testStepExecutionStatistics.setRequestCount(BigInteger.valueOf(testStepsMetrics.getCount()));
        testStepExecutionStatistics.setTransactionsPerSecond(BigInteger.valueOf(testStepsMetrics.getTps()));
        BigInteger errorsPerSecond = BigInteger.valueOf(testStepsMetrics.getErr()).subtract(lastErrCount);
        testStepExecutionStatistics.setErrorsPerSecond(errorsPerSecond);
        return testStepExecutionStatistics;
    }


    public List<TestStepExecutionStatistics> convertLoadUITestStepsItem(LoadUITestStepsItem item) {
        List<TestStepExecutionStatistics> results = new ArrayList<>();
        for (TestStatistics statistics : item) {
            TestStepExecutionStatistics testStepExecutionStatistics = convertTestStepStatistics(statistics);
            results.add(testStepExecutionStatistics);
        }
        return results;
    }

    protected static TestStepExecutionStatistics convertTestStepStatistics(TestStatistics testStatistics) {
        TestStepExecutionStatistics testStepExecutionStatistics = new TestStepExecutionStatistics();
//        testStepExecutionStatistics.set(BigInteger.valueOf(testStatistics.getTimeSec()));
//        testStepExecutionStatistics.setMinRequestTimeMs(BigInteger.valueOf(testStatistics.getMinMs()));
//        testStepExecutionStatistics.setMaxRequestTimeMs(BigInteger.valueOf(testStatistics.getMaxMs()));
//        testStepExecutionStatistics.setMedianRequestTimeMs(BigInteger.valueOf(testStatistics.getMedianMs()));
//        testStepExecutionStatistics.setLastRequestTimeMs(BigInteger.valueOf(testStatistics.getLastMs()));
//        testStepExecutionStatistics.setRequestCount(BigInteger.valueOf(testStatistics.getCount()));
//        testStepExecutionStatistics.setTransactionsPerSecond(BigInteger.valueOf(testStatistics.getTps()));
//        BigInteger errorsPerSecond = BigInteger.valueOf(testStatistics.getErr()).subtract(lastErrCount);
//        testStepsMetricsEntity.setErrorsPerSecond(errorsPerSecond);
        return testStepExecutionStatistics;
    }

}
