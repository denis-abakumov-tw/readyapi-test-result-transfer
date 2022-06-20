package ca.thoughtwire.readyapi.testresulttransfer.xml.loaduiteststepshistory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
public class LoadUITestStepsHistoryItem implements Iterable<TestStepsMetrics> {

    @JacksonXmlProperty
    private String testStepName;

    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(localName = "testStepsMetricsDataSource")
    private List<TestStepsMetrics> testStepsMetrics = new ArrayList<>();

    @Override
    public Iterator<TestStepsMetrics> iterator() {
        return this.testStepsMetrics == null ? Collections.emptyIterator() : this.testStepsMetrics.iterator();
    }

}
