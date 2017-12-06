package uwr.onlinejudge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
