package ca.thoughtwire.readyapi.testresult.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonPropertyOrder({"performanceTest"})
public class PerformanceTestImportResult implements Serializable {

    @JsonProperty("performanceTest")
    private final String name;

    private List<ScenarioImportResult> scenarios = new ArrayList<>();

}
