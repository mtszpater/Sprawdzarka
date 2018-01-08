package uwr.onlinejudge.server.models.form;

import org.springframework.format.annotation.DateTimeFormat;
import uwr.onlinejudge.server.models.TaskDescription;
import uwr.onlinejudge.server.models.TaskList;
import uwr.onlinejudge.server.models.User;

import javax.persistence.*;
import java.util.Date;

public class TaskForm {

    private TaskList taskList;
    private TaskDescription taskDescription;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deadline;
    private User user;
    private String comment;

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(TaskDescription taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
