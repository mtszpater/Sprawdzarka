package uwr.onlinejudge.server.models.form;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import uwr.onlinejudge.server.models.TaskDescription;
import uwr.onlinejudge.server.models.TaskList;
import uwr.onlinejudge.server.models.User;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TaskForm {

    private TaskList taskList;
    @Length(min = 3, message = "Tytuł zadania musi mieć conajmniej 3 znaki")
    private String name;
    private TaskDescription taskDescription;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Zadanie nie może być na wczoraj")
    @NotNull(message = "Musisz uzupełnić datę")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
