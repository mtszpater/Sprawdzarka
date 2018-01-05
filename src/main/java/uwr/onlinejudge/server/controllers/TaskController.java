package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.TaskDescription;
import uwr.onlinejudge.server.models.TaskList;
import uwr.onlinejudge.server.models.form.TaskDescriptionForm;
import uwr.onlinejudge.server.models.form.TaskForm;
import uwr.onlinejudge.server.models.form.TaskListForm;
import uwr.onlinejudge.server.services.GroupService;
import uwr.onlinejudge.server.services.TaskService;
import uwr.onlinejudge.server.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;


@Controller
@RequestMapping
public class TaskController {
    private TaskService taskService;
    private UserService userService;
    private GroupService groupService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService, GroupService groupService) {
        this.taskService = taskService;
        this.userService = userService;
        this.groupService = groupService;
    }

    @RequestMapping(value = "/dodaj_liste/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addList(@PathVariable("id") Long id, Model model) {
        Group group = groupService.getGroup(id);
        if (group == null)
            return "error_page";

        TaskListForm taskListForm = new TaskListForm();
        taskListForm.setGroup(group);

        model.addAttribute("taskList", taskListForm);
        return "forms/add_list";
    }

    @RequestMapping(value = "/dodaj_liste", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveList(@ModelAttribute("taskList") @Valid TaskListForm taskListForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "forms/add_list";
        }

        taskListForm.setUser(userService.findByEmail(principal.getName()));
        taskService.save(taskListForm);

        return "redirect:/grupa/" + taskListForm.getGroup().getId();
    }

    @RequestMapping(value = "/dodaj_opis_zadania", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addTaskDescription(Model model) {
        model.addAttribute("taskDescription", new TaskDescriptionForm());
        return "forms/add_task_description";
    }

    @RequestMapping(value = "/dodaj_opis_zadania", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addTaskDescription(@ModelAttribute("taskDescription") @Valid TaskDescriptionForm taskDescriptionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "forms/add_task_description";
        }

        taskDescriptionForm.setUser(userService.findByEmail(principal.getName()));
        taskService.save(taskDescriptionForm);

        return "redirect:/dodaj_opis_zadania";
    }

    @RequestMapping(value = "/dodaj_zadanie/{taskListId}/{taskDescriptionId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addTask(@PathVariable("taskListId") Long taskListId, @PathVariable("taskDescriptionId") Long taskDescriptionId, Model model)  {
        TaskDescription taskDescription = taskService.getTaskDescription(taskDescriptionId);
        TaskList taskList = taskService.getTaskList(taskListId);

        if(taskDescription == null || taskList == null)
            return "error_page";

        TaskForm taskForm = new TaskForm();
        taskForm.setTaskDescription(taskDescription);
        taskForm.setTaskList(taskList);


        model.addAttribute("task", taskForm);
        return "forms/add_task";
    }

    @RequestMapping(value = "/dodaj_zadanie_do_listy/{taskListId}", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String index(@PathVariable("taskListId") Long taskListId, Model model, Principal principal) {
        TaskList taskList = taskService.getTaskList(taskListId);

        if(taskList == null)
            return "error_page";


        Collection<TaskDescription> taskDescriptions = taskService.getTaskDescriptions();
        model.addAttribute("taskDescriptions", taskDescriptions);
        return "add_task_to_list";
    }

}