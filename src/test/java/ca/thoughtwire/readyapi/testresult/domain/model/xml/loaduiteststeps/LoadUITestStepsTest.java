package ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps;

import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.LoadUITestSteps;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.LoadUITestStepsItem;
import ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststeps.TestStatistics;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.math.BigDecimal;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class LoadUITestStepsTest {

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    @BeforeClass
    public static void setup() {
        XML_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Disabled
    @Test
    public void serializeLoadUITestStepsXml() throws Exception {
        LoadUITestSteps loadUITestSteps = new LoadUITestSteps();
        LoadUITestStepsItem loadUITestStepsItem = new LoadUITestStepsItem();
        loadUITestSteps.getLoadUITestStepsItems().add(loadUITestStepsItem);
        loadUITestStepsItem.setPerformanceTestName("KPI Service API Test Suite");
        loadUITestStepsItem.setTestCaseName("/kpi/getActionCards");
        TestStatistics testStatistics = new TestStatistics("/kpi/getActionCards Request", 1202, 30, 271, 40,
                null, null, null, 0, new BigDecimal("0.0"), new BigDecimal("38.0"),
                new BigDecimal("47.0"), new BigDecimal("77.54999999999973"), new BigDecimal("114.82000000000016"),
                new BigDecimal("35.0"), 35, new BigDecimal("9.0"));
        loadUITestStepsItem.setTestStatistics(testStatistics);
        TestStatistics testCaseLevelStatistics = new TestStatistics("Test Case Level", 1203, 30, 1077, 41,
                new BigDecimal("36.31705245546093"), new BigDecimal("0.7317073170731707"), new BigDecimal("26.26829268292683"),
                0, new BigDecimal("0.0"), new BigDecimal("38.0"), new BigDecimal("47.0"), new BigDecimal("78.0"), new BigDecimal("119.60000000000036"),
                new BigDecimal("35.0"), 1077, new BigDecimal("9.566143961321925"));
        loadUITestStepsItem.setTestCaseLevelStatistics(testCaseLevelStatistics);
        String xml = XML_MAPPER.writeValueAsString(loadUITestSteps);
        String expected = "<LoadUITestSteps>\n" +
                "  <item>\n" +
                "    <testCaseName>KPI Service API Test Suite:/kpi/getActionCards</testCaseName>\n" +
                "    <testStepsTPSDataSource>\n" +
                "      <item>\n" +
                "        <cnt>1202</cnt>\n" +
                "        <min>30</min>\n" +
                "        <max>271</max>\n" +
                "        <avg>40</avg>\n" +
                "        <failure>0</failure>\n" +
                "        <percentile75>38.0</percentile75>\n" +
                "        <percentile90>47.0</percentile90>\n" +
                "        <percentile95>77.54999999999973</percentile95>\n" +
                "        <percentile98>114.82000000000016</percentile98>\n" +
                "        <median>35.0</median>\n" +
                "        <last>35</last>\n" +
                "        <tps>9.0</tps>\n" +
                "        <TestStep>/kpi/getActionCards Request</TestStep>\n" +
                "        <failure_ratio>0.0</failure_ratio>\n" +
                "      </item>\n" +
                "      <item>\n" +
                "        <cnt>1203</cnt>\n" +
                "        <min>30</min>\n" +
                "        <max>1077</max>\n" +
                "        <avg>41</avg>\n" +
                "        <minAvg>0.7317073170731707</minAvg>\n" +
                "        <maxAvg>26.26829268292683</maxAvg>\n" +
                "        <failure>0</failure>\n" +
                "        <percentile75>38.0</percentile75>\n" +
                "        <percentile90>47.0</percentile90>\n" +
                "        <percentile95>78.0</percentile95>\n" +
                "        <percentile98>119.60000000000036</percentile98>\n" +
                "        <median>35.0</median>\n" +
                "        <last>1077</last>\n" +
                "        <tps>9.566143961321925</tps>\n" +
                "        <TestStep>Test Case Level</TestStep>\n" +
                "        <std-dev>36.31705245546093</std-dev>\n" +
                "        <failure_ratio>0.0</failure_ratio>\n" +
                "      </item>\n" +
                "    </testStepsTPSDataSource>\n" +
                "  </item>\n" +
                "</LoadUITestSteps>\n";
//        assertEquals(expected, xml);
    }

    @Test
    public void deserializeLoadUITestStepsXml() throws Exception {
        LoadUITestSteps loadUITestSteps = XML_MAPPER.readValue(Path.of("src/test/resources/KPI Service/LoadUITestSteps.xml").toFile(), LoadUITestSteps.class);
        assertNotNull(loadUITestSteps);
        assertFalse(loadUITestSteps.getLoadUITestStepsItems().isEmpty());
        assertNotNull(loadUITestSteps.getLoadUITestStepsItems().get(0));
        assertNotNull(loadUITestSteps.getLoadUITestStepsItems().get(0).getTestCaseName());
        assertNotNull(loadUITestSteps.getLoadUITestStepsItems().get(0).getTestStatistics());
        assertNotNull(loadUITestSteps.getLoadUITestStepsItems().get(0).getTestCaseLevelStatistics());
    }

}
