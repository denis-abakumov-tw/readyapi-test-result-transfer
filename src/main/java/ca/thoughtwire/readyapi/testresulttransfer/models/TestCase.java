package ca.thoughtwire.readyapi.testresulttransfer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "test_case")
@NoArgsConstructor
@Getter
@Setter
public class TestCase {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestSuite testSuite;

    @OneToMany
    private Collection<TestCaseExecution> testCaseExecutions;

    public TestCase(String testCaseName) {
        this.name = testCaseName;
    }

}
