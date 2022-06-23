package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.TestStep;
import ca.thoughtwire.readyapi.testresult.domain.repository.TestStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestStepService {

    @Autowired
    private TestStepRepository testStepRepository;

    public List<TestStep> list() {
        return testStepRepository.findAll();
    }

    public TestStep findByName(String testStepName) {
        return testStepRepository.findByName(testStepName);
    }

    public TestStep findOrCreate(String testStepName) {
        TestStep testStep = testStepRepository.findByName(testStepName);
        return testStep == null ? testStepRepository.save(new TestStep(testStepName)) : testStep;
    }

}