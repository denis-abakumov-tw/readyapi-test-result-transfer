package ca.thoughtwire.readyapi.testresult.domain.repository;

import ca.thoughtwire.readyapi.testresult.domain.model.TestCaseExecutionMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseExecutionMetricsRepository extends JpaRepository<TestCaseExecutionMetrics, Integer> {

}