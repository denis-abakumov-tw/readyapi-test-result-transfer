package ca.thoughtwire.readyapi.testresult.domain.repository;

import ca.thoughtwire.readyapi.testresult.domain.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Integer> {

    TestCase findByName(String scenarioName);

}