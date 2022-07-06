package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "scenario_execution_result")
@NoArgsConstructor
@Getter
@Setter
public class ScenarioExecutionResult {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "scenario_execution_id", nullable = false)
    private ScenarioExecution scenarioExecution;

}
