package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTypeServiceTest {

    @Autowired
    private TestTypeService testTypeService;

    @Test
    public void whenGetAllTestTypes_thenTestTypesAreReturned() {
        List<TestType> testTypes = testTypeService.list();
        Assert.assertNotNull(testTypes);
        Assert.assertFalse(testTypes.isEmpty());
        Assert.assertNotNull(testTypes.get(0));
    }

}
