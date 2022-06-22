package ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps;

import ca.thoughtwire.readyapi.testresult.domain.model.xml.TestItemName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

@JsonIgnoreProperties("testStepsMetricsDataSource")
public class LoadUITestStepsItem implements Iterable<TestStatistics> {

    @JacksonXmlProperty(localName = "testCaseName")
    private final TestItemName testCaseName = new TestItemName(2);

    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(localName = "testStepsTPSDataSource")
    private final TestStatistics[] testStatistics = {null, null};

    @Override
    public Iterator<TestStatistics> iterator() {
        return this.testStatistics == null ? Collections.emptyIterator() : Arrays.stream(this.testStatistics).iterator();
    }

    @JsonIgnore
    public TestStatistics getTestStatistics() {
        return testStatistics[0];
    }

    @JsonIgnore
    public void setTestStatistics(TestStatistics testStatistics) {
        this.testStatistics[0] = testStatistics;
    }

    @JsonIgnore
    public TestStatistics getTestCaseLevelStatistics() {
        return testStatistics[1];
    }

    @JsonIgnore
    public void setTestCaseLevelStatistics(TestStatistics testStatistics) {
        this.testStatistics[1] = testStatistics;
    }

    @JsonIgnore
    public String getPerformanceTestName() {
        return testCaseName.getPart(0);
    }

    @JsonIgnore
    public void setPerformanceTestName(String name) {
        testCaseName.setPart(0, name);
    }

    @JsonIgnore
    public String getTestCaseName() {
        return testCaseName.getPart(1);
    }

    @JsonIgnore
    public void setTestCaseName(String name) {
        testCaseName.setPart(1, name);
    }

}