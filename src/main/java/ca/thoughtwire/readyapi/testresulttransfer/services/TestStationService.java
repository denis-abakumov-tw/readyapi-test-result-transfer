package ca.thoughtwire.readyapi.testresulttransfer.services;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestStation;
import ca.thoughtwire.readyapi.testresulttransfer.repositories.TestStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestStationService {

    @Autowired
    private TestStationRepository testStationRepository;

    public List<TestStation> list() {
        return testStationRepository.findAll();
    }

}
