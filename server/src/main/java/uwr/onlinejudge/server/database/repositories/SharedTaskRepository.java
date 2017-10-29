package uwr.onlinejudge.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.database.models.SharedTask;
import uwr.onlinejudge.server.database.models.User;

import java.util.List;

public interface SharedTaskRepository extends JpaRepository<SharedTask, Long> {
    SharedTask findById(long id);

    List<SharedTask> findByUser(User user);
}
