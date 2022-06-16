package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestStation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStationServiceTest {

    @Autowired
    private TestStationService testStationService;

    @Test
    public void whenGetAllTestStations_thenTestStationsAreReturned() {
        List<TestStation> testStations = testStationService.list();
        Assert.assertNotNull(testStations);
        Assert.assertFalse(testStations.isEmpty());
        Assert.assertNotNull(testStations.get(0));
    }

}
