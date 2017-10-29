package uwr.onlinejudge.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.database.models.Group;
import uwr.onlinejudge.server.database.models.Registration;
import uwr.onlinejudge.server.database.models.User;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Registration findById(long id);

    List<Registration> findByGroup(Group group);

    List<Registration> findByUser(User user);
}
