package ca.thoughtwire.readyapi.testresulttransfer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TestExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime started;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_case_id", nullable = false)
    private TestCase testCase;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_station_id", nullable = false)
    private TestStation testStation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_execution_id")
    private Set<PerformanceResult> performanceResults;

    public TestExecution(Long id) {
        this.id = id;
    }

    public Set<PerformanceMetric> getAllMetrics() {
        return performanceResults.stream().map(PerformanceResult::getPerformanceMetric).collect(Collectors.toSet());
    }

}
