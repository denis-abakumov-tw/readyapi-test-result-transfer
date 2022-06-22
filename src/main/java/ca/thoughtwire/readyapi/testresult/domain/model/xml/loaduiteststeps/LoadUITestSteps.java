package ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JacksonXmlRootElement
@Getter
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

}
