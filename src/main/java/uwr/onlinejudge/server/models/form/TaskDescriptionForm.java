package uwr.onlinejudge.server.models.form;

import org.hibernate.validator.constraints.Length;
import uwr.onlinejudge.server.models.User;

public class TaskDescriptionForm {
    private User user;
    @Length(min = 3, message = "Tytuł zadania powinien mieć conajmniej 3 znaki")
    private String name;
    @Length(min = 3, message = "Treść zadania nie powinna być pusta")
    private String content;
    private boolean personal;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPersonal() {
        return personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }
}
