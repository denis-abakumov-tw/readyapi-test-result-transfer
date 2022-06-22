package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.TestType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTypeServiceTest {

    @Autowired
    private TestTypeService testTypeService;

    @Test
    public void whenFindAll_ThenAllTestTypesAreReturned() {
        List<TestType> testTypeList = testTypeService.list();
        assertFalse(testTypeList.isEmpty());
    }

    @Test
    public void whenFindOrCreateExistingTestType_ThenExistingTestTypeIsReturned() {
        final String testTypeName = "Performance";
        TestType testType = testTypeService.findByName(testTypeName);
        assertNotNull(testType);
        testType = testTypeService.findByNameOrCreate(testTypeName);
        assertNotNull(testType);
        assertEquals(testTypeName, testType.getName());
    }

    @Test
    public void whenFindOrCreateNonExistingTestType_ThenNewTestTypeIsReturned() {
        final String testTypeName = "New Test Type";
        TestType testType = testTypeService.findByName(testTypeName);
        assertNull(testType);
        testType = testTypeService.findByNameOrCreate(testTypeName);
        assertNotNull(testType);
        assertEquals(testTypeName, testType.getName());
    }

}
