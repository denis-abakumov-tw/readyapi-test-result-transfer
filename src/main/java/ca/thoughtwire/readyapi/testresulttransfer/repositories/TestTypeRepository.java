package ca.thoughtwire.readyapi.testresulttransfer.repositories;

import ca.thoughtwire.readyapi.testresulttransfer.models.TestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTypeRepository extends JpaRepository<TestType, Integer> {

    TestType findByName(String testTypeName);

}