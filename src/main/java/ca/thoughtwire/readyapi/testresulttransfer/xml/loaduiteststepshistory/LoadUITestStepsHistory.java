package ca.thoughtwire.readyapi.testresulttransfer.xml.loaduiteststepshistory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JacksonXmlRootElement
@Getter
public class LoadUITestStepsHistory implements Iterable<LoadUITestStepsHistoryItem> {

    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<LoadUITestStepsHistoryItem> loadUITestStepsHistoryItems = new ArrayList<>();

    @Override
    public Iterator<LoadUITestStepsHistoryItem> iterator() {
        return this.loadUITestStepsHistoryItems.iterator();
    }

}
