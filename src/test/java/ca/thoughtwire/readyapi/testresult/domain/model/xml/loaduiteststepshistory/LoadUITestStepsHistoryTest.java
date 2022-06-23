package ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Path;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LoadUITestStepsHistoryTest {

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    @BeforeClass
    public static void setup() {
        XML_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    public void serializeLoadUITestStepsHistoryXml() throws Exception {
        LoadUITestStepsHistory loadUITestStepsHistory = new LoadUITestStepsHistory();
        LoadUITestStepsHistoryItem loadUITestStepsHistoryItem = new LoadUITestStepsHistoryItem();
        loadUITestStepsHistory.getLoadUITestStepsHistoryItems().add(loadUITestStepsHistoryItem);
        loadUITestStepsHistoryItem.setScenarioName("REST");
        loadUITestStepsHistoryItem.setTestCaseName("/kpi/getActionCards");
        loadUITestStepsHistoryItem.setTestStepName("/kpi/getActionCards Request");
        PerformanceMetrics performanceMetrics = new PerformanceMetrics(0, 35, 35, 35, 35, 0, 0, 0);
        loadUITestStepsHistoryItem.getPerformanceMetrics().add(performanceMetrics);
        String xml = XML_MAPPER.writeValueAsString(loadUITestStepsHistory);
        String expected = "<LoadUITestStepsHistory>\n" +
                "  <item>\n" +
                "    <testStepName>REST:/kpi/getActionCards:/kpi/getActionCards Request</testStepName>\n" +
                "    <testStepsMetricsDataSource>\n" +
                "      <item>\n" +
                "        <TimeSec.>0</TimeSec.>\n" +
                "        <MinMs>35</MinMs>\n" +
                "        <MaxMs>35</MaxMs>\n" +
                "        <MedianMs>35</MedianMs>\n" +
                "        <LastMs>35</LastMs>\n" +
                "        <Count>0</Count>\n" +
                "        <TPS>0</TPS>\n" +
                "        <Err>0</Err>\n" +
                "      </item>\n" +
                "    </testStepsMetricsDataSource>\n" +
                "  </item>\n" +
                "</LoadUITestStepsHistory>\n";
        assertEquals(expected, xml);
    }

    @Test
    public void deserializeLoadUITestStepsHistoryXml() throws Exception {
        LoadUITestStepsHistory loadUITestStepsHistory = XML_MAPPER.readValue(Path.of("src/test/resources/KPI Service/LoadUITestStepsHistory.xml").toFile(), LoadUITestStepsHistory.class);
        assertNotNull(loadUITestStepsHistory);
        assertFalse(loadUITestStepsHistory.getLoadUITestStepsHistoryItems().isEmpty());
        assertNotNull(loadUITestStepsHistory.getLoadUITestStepsHistoryItems().get(0));
        assertNotNull(loadUITestStepsHistory.getLoadUITestStepsHistoryItems().get(0).getTestStepName());
        assertFalse(loadUITestStepsHistory.getLoadUITestStepsHistoryItems().get(0).getPerformanceMetrics().isEmpty());
        assertNotNull(loadUITestStepsHistory.getLoadUITestStepsHistoryItems().get(0).getPerformanceMetrics().get(0));
    }

}
