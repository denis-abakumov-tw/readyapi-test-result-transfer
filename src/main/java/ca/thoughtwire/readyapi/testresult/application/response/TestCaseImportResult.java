package ca.thoughtwire.readyapi.testresult.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonPropertyOrder({"testCase"})
public class TestCaseImportResult {

    @JsonProperty("testCase")
    private final String name;

    private List<TestStepImportResult> testSteps = new ArrayList<>();

    private int metrics;

    private boolean statisticsImported;

}
