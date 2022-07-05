package ca.thoughtwire.readyapi.testresult.application.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonPropertyOrder({"status", "performanceTest"})
public class PerformanceTestImportResult implements Serializable {

    public static final String ALREADY_EXISTS = "Already exists";

    public static final String SUCCESSFULLY_IMPORTED = "Successfully imported";

    private String status;

    private String environment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
    private LocalDateTime created;

    @JsonProperty("performanceTest")
    private final String name;

    private List<ScenarioImportResult> scenarios = new ArrayList<>();

}
