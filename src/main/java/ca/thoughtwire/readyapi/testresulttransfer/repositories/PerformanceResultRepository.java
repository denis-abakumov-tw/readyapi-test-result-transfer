package ca.thoughtwire.readyapi.testresulttransfer.repositories;

import ca.thoughtwire.readyapi.testresulttransfer.models.PerformanceResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceResultRepository extends JpaRepository<PerformanceResult, Long> {

    List<PerformanceResult> findAllByTestExecution_IdAndPerformanceMetric_Id(Long testExecutionId, Long performanceMetricId);

}
