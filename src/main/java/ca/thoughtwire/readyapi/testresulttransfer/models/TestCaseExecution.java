package ca.thoughtwire.readyapi.testresulttransfer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "test_case_execution")
@NoArgsConstructor
@Getter
@Setter
public class TestCaseExecution {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @OneToMany
    private Collection<PerformanceResult> performanceResults;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestSuiteExecution testSuiteExecution;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestCase testCase;

}
