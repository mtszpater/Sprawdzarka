package uwr.onlinejudge.server.services;

import uwr.onlinejudge.server.models.*;
import uwr.onlinejudge.server.models.form.TaskDescriptionForm;
import uwr.onlinejudge.server.models.form.TaskForm;
import uwr.onlinejudge.server.models.form.TaskListForm;

import java.util.Collection;

public interface TaskService {
    Collection<TaskList> getTaskLists(Group group);

    void save(TaskListForm taskListForm);

    void save(TaskDescriptionForm taskDescriptionForm);

    TaskDescription getTaskDescription(Long id);

    TaskList getTaskList(Long id);

    Collection<TaskDescription> getTaskDescriptions();

    Task getTask(Long id);

    void save(TaskForm taskForm);

    Collection<Test> getTests(Task task);

    Collection<Solution> getSolutions(User user, Task task);

    Score getScore(Long id);

    Solution getSolution(Long id);

    Collection<Language> getLanguages(Task task);

    Language getLanguage(Long id);
}
