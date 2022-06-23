package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.TestCase;
import ca.thoughtwire.readyapi.testresult.domain.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    public List<TestCase> list() {
        return testCaseRepository.findAll();
    }

    public TestCase findByName(String testCaseName) {
        return testCaseRepository.findByName(testCaseName);
    }

    public TestCase findOrCreate(String testCaseName) {
        TestCase testCase = testCaseRepository.findByName(testCaseName);
        return testCase == null ? testCaseRepository.save(new TestCase(testCaseName)) : testCase;
    }

}