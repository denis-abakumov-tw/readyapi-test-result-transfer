package ca.thoughtwire.readyapi.testresult.domain.model.xml.loaduiteststepshistory;

import ca.thoughtwire.readyapi.testresult.infrastructure.configuration.IntDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PerformanceMetrics {

    @JacksonXmlProperty(localName = "TimeSec.")
    @JsonDeserialize(using = IntDeserializer.class)
    private int timeSec;

    @JacksonXmlProperty(localName = "MinMs")
    @JsonDeserialize(using = IntDeserializer.class)
    private int minMs;

    @JacksonXmlProperty(localName = "MaxMs")
    @JsonDeserialize(using = IntDeserializer.class)
    private int maxMs;

    @JacksonXmlProperty(localName = "MedianMs")
    @JsonDeserialize(using = IntDeserializer.class)
    private int medianMs;

    @JacksonXmlProperty(localName = "LastMs")
    @JsonDeserialize(using = IntDeserializer.class)
    private int lastMs = 0;

    @JacksonXmlProperty(localName = "Count")
    @JsonDeserialize(using = IntDeserializer.class)
    private int count;

    @JacksonXmlProperty(localName = "TPS")
    @JsonDeserialize(using = IntDeserializer.class)
    private int tps;

    @JacksonXmlProperty(localName = "Err")
    @JsonDeserialize(using = IntDeserializer.class)
    private int err;

}
