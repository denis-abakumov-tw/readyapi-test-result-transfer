package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestSuite;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestType;
import ca.thoughtwire.readyapi.testresulttransfer.repositories.TestSuiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestSuiteService {

    @Autowired
    private TestSuiteRepository testSuiteRepository;

    public List<TestSuite> list() {
        return testSuiteRepository.findAll();
    }

    public TestSuite findOrCreate(String testSuiteName, TestType testType) {
        TestSuite testSuite = testSuiteRepository.findByName(testSuiteName);
        if (testSuite == null) {
            testSuite = new TestSuite(testSuiteName);
            testSuite.setTestType(testType);
            testSuiteRepository.save(testSuite);
        }
        return testSuite;
    }

    public TestSuite findByName(String testSuiteName) {
        return testSuiteRepository.findByName(testSuiteName);
    }

}