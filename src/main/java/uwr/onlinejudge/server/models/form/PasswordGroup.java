package uwr.onlinejudge.server.models.form;

import org.hibernate.validator.constraints.NotEmpty;

public class PasswordGroup {
    @NotEmpty(message = "To pole nie moze być puste")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
