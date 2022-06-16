package ca.thoughtwire.readyapi.testresulttransfer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "test_type_id", nullable = false)
    private TestType testType;

    public TestCase(Long id) {
        this.id = id;
    }

    public TestCase(String name, TestType testType) {
        this.name = name;
        this.testType = testType;
    }

}
