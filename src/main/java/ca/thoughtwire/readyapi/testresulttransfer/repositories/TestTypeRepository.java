package ca.thoughtwire.readyapi.testresulttransfer.repositories;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestTypeRepository extends JpaRepository<TestType, Long> {

    boolean existsByName(String name);

    Optional<TestType> findByName(String name);

}
