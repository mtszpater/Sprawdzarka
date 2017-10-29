package uwr.onlinejudge.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.database.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findById(long id);
}
