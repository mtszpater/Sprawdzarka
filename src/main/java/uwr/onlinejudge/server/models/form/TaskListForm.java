package uwr.onlinejudge.server.models.form;

import org.hibernate.validator.constraints.NotEmpty;
import uwr.onlinejudge.server.models.User;

public class TaskListForm {

    private User user;

    @NotEmpty(message = "To pole nie moze być puste")
    private String name;

    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}