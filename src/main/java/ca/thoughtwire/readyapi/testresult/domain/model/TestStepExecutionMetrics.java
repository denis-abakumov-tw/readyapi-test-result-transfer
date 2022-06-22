package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "test_step_execution_metrics")
@NoArgsConstructor
@Getter
@Setter
public class TestStepExecutionMetrics {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestStepExecution testStepExecution;

    @Basic
    @Column(name = "time_sec")
    private BigInteger secondsFromStart;

    @Basic
    @Column(name = "min_ms")
    private BigInteger minRequestTimeMs;

    @Basic
    @Column(name = "max_ms")
    private BigInteger maxRequestTimeMs;

    @Basic
    @Column(name = "median_ms")
    private BigInteger medianRequestTimeMs;

    @Basic
    @Column(name = "last_ms")
    private BigInteger lastRequestTimeMs;

    @Basic
    @Column(name = "cnt")
    private BigInteger requestCount;

    @Basic
    @Column(name = "tps")
    private BigInteger transactionsPerSecond;

    @Basic
    @Column(name = "err")
    private BigInteger errorsPerSecond;

}
