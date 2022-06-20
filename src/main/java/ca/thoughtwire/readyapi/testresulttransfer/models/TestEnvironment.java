package ca.thoughtwire.readyapi.testresulttransfer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "test_environment")
@NoArgsConstructor
@Getter
@Setter
public class TestEnvironment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany
    private Collection<TestSuiteExecution> testSuiteExecutions;

    public TestEnvironment(String testEnvironmentName) {
        this.name = testEnvironmentName;
    }

}
