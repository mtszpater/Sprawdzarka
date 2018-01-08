package uwr.onlinejudge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.User;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByUser(User user);
}
