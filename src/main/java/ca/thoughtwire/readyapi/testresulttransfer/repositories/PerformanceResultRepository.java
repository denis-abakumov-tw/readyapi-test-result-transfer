package ca.thoughtwire.readyapi.testresulttransfer.repositories;

import ca.thoughtwire.readyapi.testresulttransfer.models.PerformanceResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceResultRepository extends JpaRepository<PerformanceResult, Integer> {

}