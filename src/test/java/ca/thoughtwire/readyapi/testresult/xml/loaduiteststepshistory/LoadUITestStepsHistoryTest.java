package ca.thoughtwire.readyapi.testresult.xml.loaduiteststepshistory;

import ca.thoughtwire.readyapi.testresult.domain.model.xml.TestItemName;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.LoadUITestStepsHistory;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.LoadUITestStepsHistoryItem;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory.TestStepsMetrics;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
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
        TestStepsMetrics testStepsMetrics = new TestStepsMetrics(0, 35, 35, 35, 35, 0, 0, 0);
        loadUITestStepsHistoryItem.getTestStepsMetrics().add(testStepsMetrics);
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
        assertFalse(loadUITestStepsHistory.getLoadUITestStepsHistoryItems().get(0).getTestStepsMetrics().isEmpty());
        assertNotNull(loadUITestStepsHistory.getLoadUITestStepsHistoryItems().get(0).getTestStepsMetrics().get(0));
    }

}
