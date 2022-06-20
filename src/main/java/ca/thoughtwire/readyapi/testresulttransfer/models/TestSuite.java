package ca.thoughtwire.readyapi.testresulttransfer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "test_suite")
@NoArgsConstructor
@Getter
@Setter
public class TestSuite {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany
    private Collection<TestCase> testCases;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestType testType;

    @OneToMany
    private Collection<TestSuiteExecution> testSuiteExecutions;

    public TestSuite(String testSuiteName) {
        this.name = testSuiteName;
    }

}
