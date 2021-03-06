package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.TestType;
import ca.thoughtwire.readyapi.testresult.domain.repository.TestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestTypeService {

    @Autowired
    private TestTypeRepository testTypeRepository;

    public List<TestType> list() {
        return testTypeRepository.findAll();
    }

    public TestType findByName(String testTypeName) {
        return testTypeRepository.findByName(testTypeName);
    }

    public TestType findByNameOrCreate(String testTypeName) {
        TestType testType = findByName(testTypeName);
        if (testType == null) {
            testType = testTypeRepository.save(new TestType(testTypeName));
        }
        return testType;
    }

}