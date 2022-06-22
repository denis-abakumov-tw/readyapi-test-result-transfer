package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "test_step")
@NoArgsConstructor
@Getter
@Setter
public class TestStep {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany
    private Collection<TestStepExecution> testStepExecutions;

    public TestStep(String testStepName) {
        this.name = testStepName;
    }

}
