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
import uwr.onlinejudge.server.models.form.TaskListForm;
import uwr.onlinejudge.server.services.GroupService;
import uwr.onlinejudge.server.services.TaskService;
import uwr.onlinejudge.server.services.UserService;

import javax.validation.Valid;
import java.security.Principal;


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
}