package ca.thoughtwire.readyapi.testresult.domain.repository;

import ca.thoughtwire.readyapi.testresult.domain.model.TestEnvironment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEnvironmentRepository extends JpaRepository<TestEnvironment, Integer> {

    TestEnvironment findByName(String testEnvironmentName);

}