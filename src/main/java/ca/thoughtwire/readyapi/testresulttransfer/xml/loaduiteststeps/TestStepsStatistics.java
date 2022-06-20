package ca.thoughtwire.readyapi.testresulttransfer.xml.loaduiteststeps;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestStepsStatistics {

    @JacksonXmlProperty(localName = "TestStep")
    private String testStep;

    @JacksonXmlProperty
    private int cnt;

    @JacksonXmlProperty
    private int min;

    @JacksonXmlProperty
    private int max;

    @JacksonXmlProperty
    private int avg;

    @JacksonXmlProperty
    private int failure;

    @JacksonXmlProperty(localName = "failure_ratio")
    private BigDecimal failureRatio;

    @JacksonXmlProperty
    private BigDecimal percentile75;

    @JacksonXmlProperty
    private BigDecimal percentile90;

    @JacksonXmlProperty
    private BigDecimal percentile95;

    @JacksonXmlProperty
    private BigDecimal percentile98;

    @JacksonXmlProperty
    private BigDecimal median;

    @JacksonXmlProperty
    private int last;

    @JacksonXmlProperty
    private BigDecimal tps;

}
