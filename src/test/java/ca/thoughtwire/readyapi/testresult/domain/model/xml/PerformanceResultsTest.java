package ca.thoughtwire.readyapi.testresult.domain.model.xml;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;

public class PerformanceResultsTest {

    @Test
    public void whenCollectResults_ThenResultsAreCollected() throws IOException {
        PerformanceResults performanceResults = new PerformanceResults();
        performanceResults.collect(Path.of("src/test/resources/KPI Service"));
    }

}
