package uwr.onlinejudge.server.models.form;

import org.hibernate.validator.constraints.NotEmpty;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.util.TestType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TestForm {
    private Task task;
    @Min(value = 1, message = "Daj mu sie wykonać")
    private int timeRequired;
    @NotEmpty(message = "Input nie może być pusty")
    private String inputArgument;
    @NotEmpty(message = "Output nie może być pusty")
    private String expectedAnswer;
    @Min(value = 1, message = "Czemu nie chcesz mu dać przynajmniej jednego punktu?")
    private int point;
    @NotNull(message = "Typ musi być wybrany")
    private TestType type;

    public TestForm() {
    }

    public TestForm(Task task) {
        this.task = task;
    }


    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(int timeRequired) {
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

