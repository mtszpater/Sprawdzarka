package uwr.onlinejudge.server.models.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {

    @Length(min = 3, message = "Nazwisko powinno posiadać conajmniej 3 litery")
    private String secondname;

    @Length(min = 3, message = "Imie powinno posiadać conajmniej 3 litery")
    private String firstname;

    @Email
    @NotEmpty(message = "To pole nie moze być puste")
    private String email;

    @Length(min = 3, message = "Hasło powinno posiadać conajmniej 3 litery")
    private String password;

    @NotEmpty(message = "To pole nie moze być puste")
    private String passwordConfirm;

    public UserForm() { }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
