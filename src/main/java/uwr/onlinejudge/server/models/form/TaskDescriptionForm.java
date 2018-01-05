package uwr.onlinejudge.server.models.form;

import uwr.onlinejudge.server.models.User;

import javax.persistence.*;

public class TaskDescriptionForm {
    private User user;
    private String name;
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
