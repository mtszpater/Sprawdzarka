package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.form.GroupForm;
import uwr.onlinejudge.server.models.form.TaskListForm;
import uwr.onlinejudge.server.services.TaskService;
import uwr.onlinejudge.server.services.UserService;
import uwr.onlinejudge.server.util.UserRole;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/listy")
public class TaskListController {

    private TaskService taskService;
    private UserService userService;

    @Autowired
    public TaskListController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @RequestMapping(value = "/dodaj_liste", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addList(Model model) {
        model.addAttribute("taskList", new TaskListForm());
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

        return "redirect:/listy";
    }

}
