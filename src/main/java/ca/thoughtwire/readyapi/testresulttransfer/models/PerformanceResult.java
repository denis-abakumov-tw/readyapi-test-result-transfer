package ca.thoughtwire.readyapi.testresulttransfer.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class PerformanceResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_execution_id", nullable = false)
    private TestExecution testExecution;

    private Long seconds;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "performance_metric_id", nullable = false)
    private PerformanceMetric performanceMetric;

    private BigDecimal amount;

}
