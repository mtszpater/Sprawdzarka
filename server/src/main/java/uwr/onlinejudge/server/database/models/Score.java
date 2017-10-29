package uwr.onlinejudge.server.database.models;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Entity
public class Score {
    @Id
    @SequenceGenerator(name = "scoreSequence", sequenceName = "score_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoreSequence")
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Solution solution;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Test test;

    @Column(nullable = false)
    private String state;

    private File score;

    @Temporal(TemporalType.TIMESTAMP)
    private Date executionTime;

    @Column(nullable = false)
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public File getScore() {
        return score;
    }

    public void setScore(File score) {
        this.score = score;
    }

    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
