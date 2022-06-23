package ca.thoughtwire.readyapi.testresult.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonPropertyOrder({"scenario"})
public class ScenarioImportResult {

    @JsonProperty("scenario")
    private final String name;

    private List<TestCaseImportResult> testCases = new ArrayList<>();

}
