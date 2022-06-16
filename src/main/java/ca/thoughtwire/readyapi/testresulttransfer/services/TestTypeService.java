package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestType;
import ca.thoughtwire.readyapi.testresulttransfer.repositories.TestTypeRepository;
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

}
