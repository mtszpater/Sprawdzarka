package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.TaskList;
import uwr.onlinejudge.server.models.form.TaskListForm;
import uwr.onlinejudge.server.repositories.TaskListRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskListRepository taskListRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public TaskServiceImpl(TaskListRepository taskListRepository, ObjectMapper objectMapper) {
        this.taskListRepository = taskListRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Collection<TaskList> getTaskLists(Group group) {
        return taskListRepository.findByGroup(group);
    }

    @Override
    public void save(TaskListForm taskListForm) {
        taskListRepository.save(objectMapper.convertValue(taskListForm, TaskList.class));
    }


}
