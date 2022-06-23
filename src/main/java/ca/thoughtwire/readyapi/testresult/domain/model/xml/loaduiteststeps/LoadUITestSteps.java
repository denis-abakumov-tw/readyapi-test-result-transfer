package ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JacksonXmlRootElement
@Getter
@Slf4j
public class LoadUITestSteps implements Iterable<LoadUITestStepsItem> {

    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<LoadUITestStepsItem> loadUITestStepsItems = new ArrayList<>();

    @Override
    public Iterator<LoadUITestStepsItem> iterator() {
        return this.loadUITestStepsItems.iterator();
    }

    @Transient
    public String getPerformanceTestName() {
        return loadUITestStepsItems.isEmpty() ? null : loadUITestStepsItems.get(0).getPerformanceTestName();
    }


    @JsonIgnore
    public Iterator<TestStatistics> testStepStatisticsIterator() {
        return new Iterator<>() {
            final Iterator<LoadUITestStepsItem> testCaseIterator = loadUITestStepsItems.iterator();

            Iterator<TestStatistics> testStepIterator = testCaseIterator.hasNext() ? testCaseIterator.next().iterator() : null;

            @Override
            public boolean hasNext() {
                return testStepIterator != null && testStepIterator.hasNext();
            }

            @Override
            public TestStatistics next() {
                if (!testStepIterator.hasNext() && testCaseIterator.hasNext()) {
                    testStepIterator = testCaseIterator.next().iterator();
                }
                TestStatistics next = testStepIterator.next();
                log.info(String.valueOf(next));
                return next;
            }
        };
    }

}
