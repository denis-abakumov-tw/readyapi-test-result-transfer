package ca.thoughtwire.readyapi.testresulttransfer.repositories;

import ca.thoughtwire.readyapi.testresulttransfer.models.PerformanceMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceMetricRepository extends JpaRepository<PerformanceMetric, Long> {

    boolean existsByName(String name);

    Optional<PerformanceMetric> findByName(String name);

}
