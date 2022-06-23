package ca.thoughtwire.readyapi.testresult.domain.model.xml;

import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.TestStatistics;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.PerformanceMetrics;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class TestCaseWrapper {

    private final String name;

    private Map<String, TestStepWrapper> testSteps = new LinkedHashMap<>();

    private List<PerformanceMetrics> metrics;

    private TestStatistics statistics;

}
