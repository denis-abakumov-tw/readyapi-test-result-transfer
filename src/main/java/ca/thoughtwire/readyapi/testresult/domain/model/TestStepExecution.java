package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "test_step_execution")
@NoArgsConstructor
@Getter
@Setter
public class TestStepExecution {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @OneToMany
    private Collection<TestStepExecutionMetrics> testStepExecutionMetrics;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestCaseExecution testCaseExecution;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestStep testStep;

}
