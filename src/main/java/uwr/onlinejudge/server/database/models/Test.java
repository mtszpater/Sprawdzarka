package uwr.onlinejudge.server.database.models;

import uwr.onlinejudge.server.database.util.TestType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Test {
    @Id
    @SequenceGenerator(name = "testSequence", sequenceName = "test_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testSequence")
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Task task;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeRequired;

    @Column(nullable = false)
    @Lob
    private String inputArgument;

    @Column(nullable = false)
    @Lob
    private String expectedAnswer;

    @Column(nullable = false)
    private int point;

    @Column(nullable = false)
    private String type = TestType.HIDDEN.toString();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(Date timeRequired) {
        this.timeRequired = timeRequired;
    }

    public String getInputArgument() {
        return inputArgument;
    }

    public void setInputArgument(String inputArgument) {
        this.inputArgument = inputArgument;
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public void setExpectedAnswer(String expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}