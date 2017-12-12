package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.User;
import uwr.onlinejudge.server.models.form.GroupForm;
import uwr.onlinejudge.server.models.form.UserForm;
import uwr.onlinejudge.server.services.GroupService;
import uwr.onlinejudge.server.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/grupy")
public class GroupController {

    private GroupService groupService;
    private UserService userService;

    @Autowired
    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        Collection<Group> groups = groupService.getGroups(userService.findByEmail(principal.getName()));
        model.addAttribute("groups", groups);
        return "group";
    }

    @RequestMapping(value = "/dodaj_grupe", method = RequestMethod.GET)
    public String addGroup(Model model) {
        model.addAttribute("group", new GroupForm());
        return "forms/add_group";
    }

    @RequestMapping(value = "/dodaj_grupe", method = RequestMethod.POST)
    public String saveGroup(@ModelAttribute("group") @Valid GroupForm groupForm, BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "forms/add_group";
        }

        groupForm.setUser(userService.findByEmail(principal.getName()));
        groupService.save(groupForm);

        return "redirect:/grupy";
    }


    @RequestMapping(value = "/grupa/{id}", method = RequestMethod.GET)
    public String showGroup(@RequestParam("id") Long id, Model model) {

        if(groupService.getGroup(id) == null)
            return "error";

        model.addAttribute("group", groupService.getGroup(id));

        return "show_group";
    }
}
