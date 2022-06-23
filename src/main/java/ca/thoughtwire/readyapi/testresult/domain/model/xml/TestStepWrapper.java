package ca.thoughtwire.readyapi.testresult.domain.model.xml;

import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.TestStatistics;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.PerformanceMetrics;
import lombok.Data;

import java.util.List;

@Data
public class TestStepWrapper {

    private final String name;

    private List<PerformanceMetrics> metrics;

    private TestStatistics statistics;

}
