package ca.thoughtwire.readyapi.testresult.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
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
    private ZonedDateTime startTime;

    @OneToMany(mappedBy = "performanceTestExecution")
    @JsonIgnore
    private Collection<ScenarioExecution> scenarioExecutions;

    @OneToMany(mappedBy = "performanceTestExecution")
    @JsonIgnore
    private Collection<PerformanceTestExecutionResult> performanceTestExecutionResults;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private PerformanceTest performanceTest;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private TestEnvironment testEnvironment;

    public PerformanceTestExecution(ZonedDateTime startTime, PerformanceTest performanceTest, TestEnvironment testEnvironment) {
        this.startTime = startTime;
        this.performanceTest = performanceTest;
        this.testEnvironment = testEnvironment;
    }

}
