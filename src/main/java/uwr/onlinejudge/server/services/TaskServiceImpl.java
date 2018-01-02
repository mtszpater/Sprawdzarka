package uwr.onlinejudge.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.TaskList;
import uwr.onlinejudge.server.repositories.TaskListRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskListRepository listRepository;

    @Autowired
    public TaskServiceImpl(TaskListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public Collection<TaskList> getTaskLists(Group group) {
        return listRepository.findByGroup(group);
    }
    

}
