package ca.thoughtwire.readyapi.testresulttransfer.repositories;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Integer> {

    TestCase findByName(String testCaseName);

}