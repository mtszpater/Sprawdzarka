package uwr.onlinejudge.server.database.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.database.models.Group;
import uwr.onlinejudge.server.database.models.Registration;
import uwr.onlinejudge.server.database.models.User;
import uwr.onlinejudge.server.database.repositories.GroupRepository;
import uwr.onlinejudge.server.database.repositories.RegistrationRepository;
import uwr.onlinejudge.server.database.repositories.UserRepository;

import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Services {
    @Autowired
    private UserRepository userRepository;

    public User getUser(String email, String password){
        User user = userRepository.findByEmail(email);
        if(matchPassword(user.getPassword(),password)){
            return user;
        }
        return null;
    }

    public void saveUser(String mail, String firstName, String lastName, String password) {
        User user = new User();
        user.setEmail(mail);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(hashPassword(password));
        userRepository.save(user);
    }

    private String hashPassword(String plainTextPassword){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(plainTextPassword);
    }

    private boolean matchPassword(String hashPassword, String plainTextPassword){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(plainTextPassword,hashPassword);
    }
}