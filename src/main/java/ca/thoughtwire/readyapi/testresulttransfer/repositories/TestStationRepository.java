package ca.thoughtwire.readyapi.testresulttransfer.repositories;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestStationRepository extends JpaRepository<TestStation, Long> {

    boolean existsByName(String name);

    Optional<TestStation> findByName(String name);

}
