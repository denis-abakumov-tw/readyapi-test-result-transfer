package ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduitestdescription;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Scenario {

    @JacksonXmlProperty
    private String profileIcon;

    @JacksonXmlProperty
    private String profileName;

    @JacksonXmlProperty
    private String scenarioName;

    @JacksonXmlProperty
    private int targetCount;

}
