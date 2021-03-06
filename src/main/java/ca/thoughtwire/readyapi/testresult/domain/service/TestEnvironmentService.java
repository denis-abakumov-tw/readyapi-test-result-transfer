package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.TestEnvironment;
import ca.thoughtwire.readyapi.testresult.domain.repository.TestEnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestEnvironmentService {

    @Autowired
    private TestEnvironmentRepository testEnvironmentRepository;

    public List<TestEnvironment> list() {
        return testEnvironmentRepository.findAll();
    }

    public TestEnvironment findByNameOrCreate(String testEnvironmentName) {
        TestEnvironment testEnvironment = testEnvironmentRepository.findByName(testEnvironmentName);
        if (testEnvironment == null) {
            testEnvironment = testEnvironmentRepository.save(new TestEnvironment(testEnvironmentName));
        }
        return testEnvironment;
    }

    public TestEnvironment findByName(String testEnvironmentName) {
        return testEnvironmentRepository.findByName(testEnvironmentName);
    }

}