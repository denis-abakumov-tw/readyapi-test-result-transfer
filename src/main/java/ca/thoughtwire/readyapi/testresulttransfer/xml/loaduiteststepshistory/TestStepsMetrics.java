package ca.thoughtwire.readyapi.testresulttransfer.xml.loaduiteststepshistory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestStepsMetrics {

    @JacksonXmlProperty(localName = "TimeSec.")
    private int timeSec;

    @JacksonXmlProperty(localName = "MinMs")
    private int minMs;

    @JacksonXmlProperty(localName = "MaxMs")
    private int maxMs;

    @JacksonXmlProperty(localName = "MedianMs")
    private int medianMs;

    @JacksonXmlProperty(localName = "LastMs")
    private int lastMs;

    @JacksonXmlProperty(localName = "Count")
    private int count;

    @JacksonXmlProperty(localName = "TPS")
    private int tps;

    @JacksonXmlProperty(localName = "Err")
    private int err;

}
