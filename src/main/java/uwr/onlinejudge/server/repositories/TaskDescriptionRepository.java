package uwr.onlinejudge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.models.TaskDescription;
import uwr.onlinejudge.server.models.User;

import java.util.List;

public interface TaskDescriptionRepository extends JpaRepository<TaskDescription, Long> {
    List<TaskDescription> findByUser(User user);
}
