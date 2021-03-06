package ca.thoughtwire.readyapi.testresult.domain.repository;

import ca.thoughtwire.readyapi.testresult.domain.model.PerformanceTest;
import ca.thoughtwire.readyapi.testresult.domain.model.PerformanceTestExecution;
import ca.thoughtwire.readyapi.testresult.domain.model.TestEnvironment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface PerformanceTestExecutionRepository extends JpaRepository<PerformanceTestExecution, Integer> {

    PerformanceTestExecution findByStartTimeAndPerformanceTestAndTestEnvironment(ZonedDateTime startTime, PerformanceTest performanceTest, TestEnvironment testEnvironment);

}