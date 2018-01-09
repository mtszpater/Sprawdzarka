package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.*;
import uwr.onlinejudge.server.models.form.TaskDescriptionForm;
import uwr.onlinejudge.server.models.form.TaskForm;
import uwr.onlinejudge.server.models.form.TaskListForm;
import uwr.onlinejudge.server.repositories.*;

import java.util.Collection;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskListRepository taskListRepository;
    private ObjectMapper objectMapper;
    private TaskDescriptionRepository taskDescriptionRepository;
    private TaskRepository taskRepository;
    private TestRepository testRepository;
    private SolutionRepository solutionRepository;

    @Autowired
    public TaskServiceImpl(TestRepository testRepository,
                           TaskListRepository taskListRepository,
                           ObjectMapper objectMapper,
                           TaskDescriptionRepository taskDescriptionRepository,
                           TaskRepository taskRepository,
                           SolutionRepository solutionRepository) {

        this.taskListRepository = taskListRepository;
        this.objectMapper = objectMapper;
        this.taskDescriptionRepository = taskDescriptionRepository;
        this.taskRepository = taskRepository;
        this.testRepository = testRepository;
        this.solutionRepository = solutionRepository;
    }

    @Override
    public Collection<TaskList> getTaskLists(Group group) {
        return taskListRepository.findByGroup(group);
    }

    @Override
    public void save(TaskListForm taskListForm) {
        taskListRepository.save(objectMapper.convertValue(taskListForm, TaskList.class));
    }

    @Override
    public void save(TaskDescriptionForm taskDescriptionForm) {
        taskDescriptionRepository.save(objectMapper.convertValue(taskDescriptionForm, TaskDescription.class));
    }

    @Override
    public TaskDescription getTaskDescription(Long id) {
        return taskDescriptionRepository.findOne(id);
    }

    @Override
    public TaskList getTaskList(Long id) {
        return taskListRepository.findOne(id);
    }

    @Override
    public Collection<TaskDescription> getTaskDescriptions() {
        return taskDescriptionRepository.findAll();
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.getOne(id);
    }

    @Override
    public void save(TaskForm taskForm) {
        taskRepository.save(objectMapper.convertValue(taskForm, Task.class));
    }

    @Override
    public Collection<Test> getTests(Task task) {
        return testRepository.findByTask(task);
    }

    @Override
    public Collection<Solution> getSolution(User user, Task task) {
        return solutionRepository.findByUserAndTask(user, task);
    }

}
