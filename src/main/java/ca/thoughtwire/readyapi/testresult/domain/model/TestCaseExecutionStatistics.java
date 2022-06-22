package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "test_case_execution_result")
@NoArgsConstructor
@Getter
@Setter
public class TestCaseExecutionStatistics {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestCaseExecution testCaseExecution;

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
    @Column(name = "std_dev")
    private BigDecimal stdDev;

    @Basic
    @Column(name = "min_avg")
    private BigDecimal minAvg;

    @Basic
    @Column(name = "max_avg")
    private BigDecimal maxAvg;

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
