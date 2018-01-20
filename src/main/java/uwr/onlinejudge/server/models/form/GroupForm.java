package uwr.onlinejudge.server.models.form;

import org.hibernate.validator.constraints.Length;
import uwr.onlinejudge.server.models.User;

public class GroupForm {

    private User user;

    @Length(min = 3, message = "Nazwa grupy powinna mieć conajmniej 3 znaki")
    private String name;

    private String description;

    private String password;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
