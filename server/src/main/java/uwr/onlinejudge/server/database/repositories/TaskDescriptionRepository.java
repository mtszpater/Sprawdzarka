package uwr.onlinejudge.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.database.models.TaskDescription;
import uwr.onlinejudge.server.database.models.User;

import java.util.List;

public interface TaskDescriptionRepository extends JpaRepository<TaskDescription, Long> {
    TaskDescription findById(long id);

    List<TaskDescription> findByUser(User user);
}
