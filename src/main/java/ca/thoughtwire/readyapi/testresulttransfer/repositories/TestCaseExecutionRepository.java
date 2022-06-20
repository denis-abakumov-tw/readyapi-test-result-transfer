package ca.thoughtwire.readyapi.testresulttransfer.repositories;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestCaseExecution;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestEnvironment;
import ca.thoughtwire.readyapi.testresulttransfer.models.TestSuite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TestCaseExecutionRepository extends JpaRepository<TestCaseExecution, Integer> {

}