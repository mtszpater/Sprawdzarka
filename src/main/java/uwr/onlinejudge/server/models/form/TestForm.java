package uwr.onlinejudge.server.models.form;

import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.util.TestType;

import java.util.Date;

public class TestForm {
    private Task task;
    private Date timeRequired;
    private String inputArgument;
    private String expectedAnswer;
    private int point;
    private String type;

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

