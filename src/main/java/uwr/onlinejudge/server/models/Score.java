package uwr.onlinejudge.server.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import uwr.onlinejudge.server.util.TestState;

import javax.persistence.*;

@Entity
public class Score {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonBackReference
    private Solution solution;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Test test;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TestState state;
    
    @Lob
    private String testResult;

    private int executionTime;

    private int point;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public TestState getState() {
        return state;
    }

    public void setState(TestState state) {
        this.state = state;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
