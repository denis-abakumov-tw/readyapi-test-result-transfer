package ca.thoughtwire.readyapi.testresulttransfer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "test_suite_execution")
@NoArgsConstructor
@Getter
@Setter
public class TestSuiteExecution {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @OneToMany
    private Collection<TestCaseExecution> testCaseExecutions;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestSuite testSuite;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TestEnvironment testEnvironment;

}
