package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "performance_test_execution")
@NoArgsConstructor
@Getter
@Setter
public class PerformanceTestExecution {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @OneToMany
    private Collection<ScenarioExecution> scenarioExecutions;

    @OneToMany
    private Collection<PerformanceTestExecutionResult> performanceTestExecutionResults;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PerformanceTest performanceTest;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestEnvironment testEnvironment;

    public PerformanceTestExecution(LocalDateTime startTime, PerformanceTest performanceTest, TestEnvironment testEnvironment) {
        this.startTime = startTime;
        this.performanceTest = performanceTest;
        this.testEnvironment = testEnvironment;
    }

}
