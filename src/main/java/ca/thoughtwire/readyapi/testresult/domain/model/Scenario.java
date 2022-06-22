package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "scenario")
@NoArgsConstructor
@Getter
@Setter
public class Scenario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany
    private Collection<ScenarioExecution> scenarioExecutions;

    public Scenario(String scenarioName) {
        this.name = scenarioName;
    }

}
