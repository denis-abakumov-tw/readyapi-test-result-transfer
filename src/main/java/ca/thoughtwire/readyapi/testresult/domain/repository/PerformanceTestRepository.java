package ca.thoughtwire.readyapi.testresult.domain.repository;

import ca.thoughtwire.readyapi.testresult.domain.model.PerformanceTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceTestRepository extends JpaRepository<PerformanceTest, Integer> {

    PerformanceTest findByName(String testSuiteName);

}