package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "performance_test")
@NoArgsConstructor
@Getter
@Setter
public class PerformanceTest {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestType testType;

    @OneToMany
    private Collection<PerformanceTestExecution> performanceTestExecutions;

    public PerformanceTest(String testSuiteName) {
        this.name = testSuiteName;
    }

}
