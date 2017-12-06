package uwr.onlinejudge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
    Test findById(long id);

    Test findByTask(Task task);
}
