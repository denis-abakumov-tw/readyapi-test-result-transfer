package ca.thoughtwire.readyapi.testresult.domain.service;

import ca.thoughtwire.readyapi.testresult.domain.model.Scenario;
import ca.thoughtwire.readyapi.testresult.domain.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioService {

    @Autowired
    private ScenarioRepository scenarioRepository;

    public List<Scenario> list() {
        return scenarioRepository.findAll();
    }

    public Scenario findByName(String scenarioName) {
        return scenarioRepository.findByName(scenarioName);
    }

    public Scenario findOrCreate(String scenarioName) {
        Scenario scenario = scenarioRepository.findByName(scenarioName);
        return scenario == null ? scenarioRepository.save(new Scenario(scenarioName)) : scenario;
    }

}