package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.User;
import uwr.onlinejudge.server.models.form.GroupForm;
import uwr.onlinejudge.server.services.GroupService;
import uwr.onlinejudge.server.services.UserService;
import uwr.onlinejudge.server.util.UserRole;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;

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
    @PreAuthorize("isFullyAuthenticated()")
    public String index(Model model, Principal principal) {
        Collection<Group> availableGroups = groupService.getAllGroups();

        User user = userService.findByEmail(principal.getName());
        Collection<Group> myGroups = groupService.getMyGroups(user);
        availableGroups.removeAll(myGroups);

        model.addAttribute("availableGroups", availableGroups);
        model.addAttribute("myGroups", myGroups);
        return "group";
    }

    @RequestMapping(value = "/dodaj_grupe", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addGroup(Model model) {
        model.addAttribute("group", new GroupForm());
        return "forms/add_group";
    }

    @RequestMapping(value = "/dodaj_grupe", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveGroup(@ModelAttribute("group") @Valid GroupForm groupForm, BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "forms/add_group";
        }

        groupForm.setUser(userService.findByEmail(principal.getName()));
        Group savedGroup = groupService.save(groupForm);

        groupService.registerUser(userService.findByEmail(principal.getName()), savedGroup, UserRole.ADMINISTRATOR);
        return "redirect:/grupy";
    }


    @RequestMapping(value = "/grupa/{id}", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String showGroup(@RequestParam("id") Long id, Model model) {

        if (groupService.getGroup(id) == null)
            return "error";

        model.addAttribute("group", groupService.getGroup(id));

        return "show_group";
    }

    @RequestMapping(value = "/zapisz_do_grupy/{id}", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String registerUser(@PathVariable("id") Long id, Principal principal) {
        if (groupService.getGroup(id) == null) {
            return "error_page";
        }

        Group group = groupService.getGroup(id);
        User user = userService.findByEmail(principal.getName());

        if (groupService.isUserRegistered(user, group)) {
            return "error_page";
        }

        groupService.registerUser(user, group, UserRole.USER);
        return "redirect:/grupy";
    }

    @RequestMapping(value = "/wypisz_z_grupy/{id}", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String unregisterFromGroup(@PathVariable("id") Long id, Principal principal) {
        if (groupService.getGroup(id) == null) {
            return "error_page";
        }

        Group group = groupService.getGroup(id);
        User user = userService.findByEmail(principal.getName());

        if (!groupService.isUserRegistered(user, group)) {
            return "error_page";
        }

        //todo: jeżeli jest adminem i sie wyrejestruje to co wtedy?
        //todo: warunek:jeśli żałożyciel grupy chce się wypisać to error

        groupService.unregisterUser(user, group);
        return "redirect:/grupy";
    }
}
