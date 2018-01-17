package uwr.onlinejudge.server.models.form;


import uwr.onlinejudge.server.models.Language;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.User;

public class SolutionForm {
    private User user;
    private Task task;
    private String solution;
    private Language language;
    private String comment;

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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
