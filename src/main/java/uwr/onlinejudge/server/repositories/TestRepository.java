package uwr.onlinejudge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.Test;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    Test findById(long id);

    List<Test> findByTask(Task task);
}
