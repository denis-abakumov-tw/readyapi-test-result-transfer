package ca.thoughtwire.readyapi.testresult.domain.model.xml;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class ScenarioWrapper {

    private final String name;

    private Map<String, TestCaseWrapper> testCases = new LinkedHashMap<>();

}
