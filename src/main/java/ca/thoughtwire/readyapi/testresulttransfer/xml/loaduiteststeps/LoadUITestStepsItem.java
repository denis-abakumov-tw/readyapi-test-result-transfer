package ca.thoughtwire.readyapi.testresulttransfer.xml.loaduiteststeps;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LoadUITestStepsItem implements Iterable<TestStepsStatistics> {

    @Getter
    @Setter
    @JacksonXmlProperty
    private String testCaseName;

    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(localName = "testStepsTPSDataSource")
    private List<TestStepsStatistics> testStepsStatistics = new ArrayList<>();

    @Override
    public Iterator<TestStepsStatistics> iterator() {
        return this.testStepsStatistics == null ? Collections.emptyIterator() : this.testStepsStatistics.iterator();
    }

    public TestStepsStatistics getTestStepsStatistics() {
        return testStepsStatistics.isEmpty() ? null : testStepsStatistics.get(0);
    }

    public TestCaseLevelStatistics getTestCaseLevelStatistics() {
        return testStepsStatistics.size() < 2 ? null : (TestCaseLevelStatistics) testStepsStatistics.get(1);
    }

}
