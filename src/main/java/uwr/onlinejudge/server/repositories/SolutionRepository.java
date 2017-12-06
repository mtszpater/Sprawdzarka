package uwr.onlinejudge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.User;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
    List<Solution> findByUserAndTask(User user, Task task);
}
