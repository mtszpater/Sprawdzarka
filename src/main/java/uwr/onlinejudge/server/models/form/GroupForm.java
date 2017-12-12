package uwr.onlinejudge.server.models.form;

import org.hibernate.validator.constraints.NotEmpty;
import uwr.onlinejudge.server.models.User;

public class GroupForm {

    private User user;

    @NotEmpty(message = "To pole nie moze być puste")
    private String name;

    private String description;

    private String password;

    private Boolean open;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
