package ca.thoughtwire.readyapi.testresult.domain.model.xml;

import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduitestdescription.LoadUITestDescription;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduitestdescription.Scenario;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.LoadUITestSteps;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.TestStatistics;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.LoadUITestStepsHistory;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.LoadUITestStepsHistoryItem;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class PerformanceResults {

    public static final String TEST_TYPE = "Performance";

    private static final String TEST_STEPS_HISTORY_XML = "LoadUITestStepsHistory.xml";

    private static final String TEST_STEPS_XML = "LoadUITestSteps.xml";

    private static final String TEST_DESCRIPTION_XML = "LoadUITestDescription.xml";

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    private LocalDateTime startTime;

    private String performanceTestName;

    private Map<String, ScenarioWrapper> scenarios = new LinkedHashMap<>();

    public void collect(Path resultsFolder) throws IOException {
        setStartTime(getFileCreationTime(resultsFolder));
        String xml;

        // LoadUITestDescription.xml: scenario names
        xml = Files.readString(resultsFolder.resolve(TEST_DESCRIPTION_XML), StandardCharsets.UTF_8);
        LoadUITestDescription loadUITestDescription = XML_MAPPER.readValue(xml, LoadUITestDescription.class);
        loadUITestDescription.getScenario().stream().map(Scenario::getScenarioName).forEach(s -> scenarios.put(s, new ScenarioWrapper(s)));

        // LoadUITestSteps.xml: performance test name, test case level statistics, and test step statistics
        xml = Files.readString(resultsFolder.resolve(TEST_STEPS_XML), StandardCharsets.UTF_8);
        LoadUITestSteps loadUITestSteps = XML_MAPPER.readValue(xml, LoadUITestSteps.class);
        setPerformanceTestName(loadUITestSteps.getPerformanceTestName());
        Iterator<TestStatistics> testStepStatisticsIterator = loadUITestSteps.testStepStatisticsIterator();

        // LoadUITestStepsHistory.xml: test case names, test step names, and test step metrics
        xml = Files.readString(resultsFolder.resolve(TEST_STEPS_HISTORY_XML), StandardCharsets.UTF_8);
        LoadUITestStepsHistory loadUITestStepsHistory = XML_MAPPER.readValue(xml, LoadUITestStepsHistory.class);
        TestStatistics testStatistics;

        for (LoadUITestStepsHistoryItem item : loadUITestStepsHistory) {
            assert testStepStatisticsIterator.hasNext();
            testStatistics = testStepStatisticsIterator.next();

            // LoadUITestStepsHistory.xml: performance metrics
            String scenarioName = item.getScenarioName();
            ScenarioWrapper scenarioWrapper = scenarios.get(scenarioName);
            TestCaseWrapper testCaseWrapper = scenarioWrapper.getTestCases().getOrDefault(item.getTestCaseName(), new TestCaseWrapper(item.getTestCaseName()));
            scenarioWrapper.getTestCases().put(item.getTestCaseName(), testCaseWrapper);
            String testStepName = item.getTestStepName();
            if (testStepName.equals("Total")) {
                testCaseWrapper.setMetrics(item.getPerformanceMetrics());
                // LoadUITestSteps.xml: test case level data
                assert testStatistics.getTestStep().equals("Test Case Level");
                testCaseWrapper.setStatistics(testStatistics);
            } else {
                TestStepWrapper testStepWrapper = testCaseWrapper.getTestSteps().getOrDefault(testStepName, new TestStepWrapper(testStepName));
                testCaseWrapper.getTestSteps().put(testStepName, testStepWrapper);
                testStepWrapper.setMetrics(item.getPerformanceMetrics());
                // LoadUITestSteps.xml: test step level data
                assert testStepName.equals(testStatistics.getTestStep());
                testStepWrapper.setStatistics(testStatistics);
            }
        }
        assert !testStepStatisticsIterator.hasNext();
        System.out.println(this);
    }

    private static LocalDateTime getFileCreationTime(Path path) throws IOException {
        FileTime fileTime = (FileTime) Files.getAttribute(path, "creationTime");
        return LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS);
    }

}
