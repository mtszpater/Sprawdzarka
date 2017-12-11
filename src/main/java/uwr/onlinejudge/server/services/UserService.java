package uwr.onlinejudge.server.services;

import uwr.onlinejudge.server.models.User;
import uwr.onlinejudge.server.models.form.UserForm;

public interface UserService {
    void save(UserForm user);

    public User findByEmail(String email);
}
