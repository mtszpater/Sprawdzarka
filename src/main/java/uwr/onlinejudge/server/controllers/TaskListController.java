package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uwr.onlinejudge.server.models.form.TaskListForm;
import uwr.onlinejudge.server.services.UserService;



@Controller
@RequestMapping("/listy")
public class TaskListController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dodaj_liste", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addList(Model model) {
        model.addAttribute("taskList", new TaskListForm());
        return "forms/add_list";
    }

}
