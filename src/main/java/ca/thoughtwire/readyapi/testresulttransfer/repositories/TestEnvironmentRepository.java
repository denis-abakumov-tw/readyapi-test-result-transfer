package ca.thoughtwire.readyapi.testresulttransfer.repositories;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestEnvironment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEnvironmentRepository extends JpaRepository<TestEnvironment, Integer> {

    TestEnvironment findByName(String testEnvironmentName);

}