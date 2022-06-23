package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "test_step_execution_statistics")
@NoArgsConstructor
@Getter
@Setter
public class TestStepExecutionStatistics {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestStepExecution testStepExecution;

    @Basic
    @Column(name = "cnt")
    private int cnt;

    @Basic
    @Column(name = "min")
    private int min;

    @Basic
    @Column(name = "max")
    private int max;

    @Basic
    @Column(name = "avg")
    private int avg;

    @Basic
    @Column(name = "failure")
    private int failure;

    @Basic
    @Column(name = "failure_ratio")
    private BigDecimal failureRatio;

    @Basic
    @Column(name = "percentile75")
    private BigDecimal percentile75;

    @Basic
    @Column(name = "percentile90")
    private BigDecimal percentile90;

    @Basic
    @Column(name = "percentile95")
    private BigDecimal percentile95;

    @Basic
    @Column(name = "percentile98")
    private BigDecimal percentile98;

    @Basic
    @Column(name = "median")
    private BigDecimal median;

    @Basic
    @Column(name = "last")
    private int last;

    @Basic
    @Column(name = "tps")
    private BigDecimal tps;

}
