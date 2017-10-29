package uwr.onlinejudge.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.database.models.Group;
import uwr.onlinejudge.server.database.models.User;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findById(long id);

    List<Group> findByUser(User user);
}
