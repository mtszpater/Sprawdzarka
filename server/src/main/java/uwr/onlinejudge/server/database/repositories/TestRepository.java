package uwr.onlinejudge.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.database.models.Task;
import uwr.onlinejudge.server.database.models.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
    Test findById(long id);

    Test findByTask(Task task);
}
