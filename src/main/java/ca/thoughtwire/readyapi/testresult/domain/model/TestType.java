package ca.thoughtwire.readyapi.testresult.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "test_type")
@NoArgsConstructor
@Getter
@Setter
public class TestType {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany
    private Collection<PerformanceTest> performancePerformanceTestsById;

    public TestType(String testTypeName) {
        this.name = testTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestType testType = (TestType) o;
        return id == testType.id && name.equals(testType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
