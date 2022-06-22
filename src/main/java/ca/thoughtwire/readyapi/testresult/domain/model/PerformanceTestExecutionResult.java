package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "performance_test_execution_result")
@NoArgsConstructor
@Getter
@Setter
public class PerformanceTestExecutionResult {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PerformanceTestExecution performanceTestExecution;

}
