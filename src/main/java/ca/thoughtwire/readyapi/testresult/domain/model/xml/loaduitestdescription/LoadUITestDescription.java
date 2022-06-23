package ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduitestdescription;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class LoadUITestDescription {

    @JacksonXmlProperty
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Scenario> scenario;

}
