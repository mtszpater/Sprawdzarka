package uwr.onlinejudge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.Registration;
import uwr.onlinejudge.server.models.User;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByGroup(Group group);

    List<Registration> findByUser(User user);
}
