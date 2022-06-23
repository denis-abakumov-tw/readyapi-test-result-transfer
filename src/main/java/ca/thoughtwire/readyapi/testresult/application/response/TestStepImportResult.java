package ca.thoughtwire.readyapi.testresult.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"testStep"})
public class TestStepImportResult {

    @JsonProperty("testStep")
    private final String name;

    private int metrics;

    private boolean statisticsImported;

}
