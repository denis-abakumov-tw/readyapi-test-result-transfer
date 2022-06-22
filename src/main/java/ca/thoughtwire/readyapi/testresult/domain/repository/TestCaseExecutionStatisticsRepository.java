package ca.thoughtwire.readyapi.testresult.domain.repository;

import ca.thoughtwire.readyapi.testresult.domain.model.TestCaseExecutionStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseExecutionStatisticsRepository extends JpaRepository<TestCaseExecutionStatistics, Integer> {

}