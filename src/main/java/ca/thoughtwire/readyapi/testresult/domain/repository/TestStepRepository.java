package ca.thoughtwire.readyapi.testresult.domain.repository;

import ca.thoughtwire.readyapi.testresult.domain.model.TestStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestStepRepository extends JpaRepository<TestStep, Integer> {

    TestStep findByName(String scenarioName);

}