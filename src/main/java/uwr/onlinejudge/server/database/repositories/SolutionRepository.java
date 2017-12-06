package uwr.onlinejudge.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.database.models.Solution;
import uwr.onlinejudge.server.database.models.Task;
import uwr.onlinejudge.server.database.models.User;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
    Solution findById(long id);

    List<Solution> findByUserAndTask(User user, Task task);
}
