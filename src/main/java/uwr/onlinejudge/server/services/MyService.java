package uwr.onlinejudge.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.TaskList;
import uwr.onlinejudge.server.repositories.TaskListRepository;

import java.util.Collection;
import java.util.Collections;

@Service
public class MyService {
    @Autowired
    private TaskListRepository listRepository;

    public Collection<TaskList> getTaskLists(Group group) {
        return listRepository.findByGroup(group);
    }

    public Collection<Task> getTasks(Group group) {
        return Collections.EMPTY_LIST;
    }

}
