package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "scenario_execution")
@NoArgsConstructor
@Getter
@Setter
public class ScenarioExecution {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "scenarioExecution")
    private Collection<ScenarioExecutionResult> scenarioExecutionResults;

    @ManyToOne
    @JoinColumn(name = "performance_test_execution_id", nullable = false)
    private PerformanceTestExecution performanceTestExecution;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Scenario scenario;

    public ScenarioExecution(PerformanceTestExecution performanceTestExecution, Scenario scenario) {
        this.performanceTestExecution = performanceTestExecution;
        this.scenario = scenario;
    }

}
