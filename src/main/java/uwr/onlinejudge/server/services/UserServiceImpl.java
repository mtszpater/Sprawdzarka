package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Role;
import uwr.onlinejudge.server.models.User;
import uwr.onlinejudge.server.models.form.UserForm;
import uwr.onlinejudge.server.repositories.RoleRepository;
import uwr.onlinejudge.server.repositories.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void save(UserForm userForm) {
        userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));

        User user = objectMapper.convertValue(userForm, User.class);

        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
