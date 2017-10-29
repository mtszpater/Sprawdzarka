package uwr.onlinejudge.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.database.models.Group;
import uwr.onlinejudge.server.database.models.TaskList;

import java.util.List;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    TaskList findById(long id);

    List<TaskList> findByGroup(Group group);
}
