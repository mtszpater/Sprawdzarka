package uwr.onlinejudge.server.services;

import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.TaskDescription;
import uwr.onlinejudge.server.models.TaskList;
import uwr.onlinejudge.server.models.form.TaskDescriptionForm;
import uwr.onlinejudge.server.models.form.TaskListForm;

import java.util.Collection;

public interface TaskService {
    Collection<TaskList> getTaskLists(Group group);

    void save(TaskListForm taskListForm);

    void save(TaskDescriptionForm taskDescriptionForm);

    TaskDescription getTaskDescription(Long id);

    TaskList getTaskList(Long id);

    Collection<TaskDescription> getTaskDescriptions();
}
