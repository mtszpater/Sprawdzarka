package uwr.onlinejudge.server.models.form;


import org.hibernate.validator.constraints.Length;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.User;
import uwr.onlinejudge.server.util.Languages;

import javax.validation.constraints.NotNull;

public class SolutionForm {
    private User user;
    private Task task;
    @Length(min = 3, message = "Nie możesz wysłać pustego programu")
    private String solution;
    @NotNull(message = "Język musi być wybrany")
    private Languages language;
    private String comment;

    public SolutionForm(Task task) {
        this.task = task;
    }

    public SolutionForm() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
