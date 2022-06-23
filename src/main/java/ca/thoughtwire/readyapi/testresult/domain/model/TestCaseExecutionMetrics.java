package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "test_case_execution_metrics")
@NoArgsConstructor
@Getter
@Setter
public class TestCaseExecutionMetrics {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestCaseExecution testCaseExecution;

    @Basic
    @Column(name = "time_sec")
    private int secondsFromStart;

    @Basic
    @Column(name = "min_ms")
    private int minRequestTimeMs;

    @Basic
    @Column(name = "max_ms")
    private int maxRequestTimeMs;

    @Basic
    @Column(name = "median_ms")
    private int medianRequestTimeMs;

    @Basic
    @Column(name = "last_ms")
    private int lastRequestTimeMs;

    @Basic
    @Column(name = "cnt")
    private int requestCount;

    @Basic
    @Column(name = "tps")
    private int transactionsPerSecond;

    @Basic
    @Column(name = "err")
    private int errorsPerSecond;

}
