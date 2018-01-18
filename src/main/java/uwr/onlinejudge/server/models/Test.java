package uwr.onlinejudge.server.models;

import uwr.onlinejudge.server.util.TestType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Test {
    @Id
    @GeneratedValue
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
    @Enumerated(EnumType.STRING)
    private TestType type;

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

    public TestType getType() {
        return type;
    }

    public void setType(TestType type) {
        this.type = type;
    }
}
