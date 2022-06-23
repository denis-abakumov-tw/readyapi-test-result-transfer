package ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory;

import ca.thoughtwire.readyapi.testresult.domain.model.xml.TestItemName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LoadUITestStepsHistoryItem implements Iterable<PerformanceMetrics> {

    @JacksonXmlProperty(localName = "testStepName")
    private final TestItemName testStepName = new TestItemName(3);

    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(localName = "testStepsMetricsDataSource")
    @Getter
    private final List<PerformanceMetrics> performanceMetrics = new ArrayList<>();

    @Override
    public Iterator<PerformanceMetrics> iterator() {
        return performanceMetrics.iterator();
    }

    @JsonIgnore
    public String getScenarioName() {
        return testStepName.getPart(0);
    }

    @JsonIgnore
    public void setScenarioName(String name) {
        testStepName.setPart(0, name);
    }

    @JsonIgnore
    public String getTestCaseName() {
        return testStepName.getPart(1);
    }

    @JsonIgnore
    public void setTestCaseName(String name) {
        testStepName.setPart(1, name);
    }

    @JsonIgnore
    public String getTestStepName() {
        return testStepName.getPart(2);
    }

    @JsonIgnore
    public void setTestStepName(String name) {
        testStepName.setPart(2, name);
    }

}
