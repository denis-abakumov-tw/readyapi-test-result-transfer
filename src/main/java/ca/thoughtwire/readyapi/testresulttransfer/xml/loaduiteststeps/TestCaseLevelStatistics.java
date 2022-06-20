package ca.thoughtwire.readyapi.testresulttransfer.xml.loaduiteststeps;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseLevelStatistics extends TestStepsStatistics {

    @JacksonXmlProperty(localName = "std-dev")
    private BigDecimal stdDev;

    @JacksonXmlProperty
    private BigDecimal minAvg;

    @JacksonXmlProperty
    private BigDecimal maxAvg;

}
