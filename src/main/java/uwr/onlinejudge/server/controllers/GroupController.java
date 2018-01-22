package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.TaskList;
import uwr.onlinejudge.server.models.User;
import uwr.onlinejudge.server.models.form.GroupForm;
import uwr.onlinejudge.server.models.form.PasswordGroup;
import uwr.onlinejudge.server.models.form.TaskListForm;
import uwr.onlinejudge.server.services.GroupService;
import uwr.onlinejudge.server.services.TaskService;
import uwr.onlinejudge.server.services.UserService;
import uwr.onlinejudge.server.util.UserRole;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class GroupController {
    private GroupService groupService;
    private UserService userService;
    private TaskService taskService;

    @Autowired
    public GroupController(GroupService groupService, UserService userService, TaskService myService) {
        this.groupService = groupService;
        this.userService = userService;
        this.taskService = myService;
    }

    @RequestMapping(value = "/grupy", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String index(Model model, Principal principal) {
        Collection<Group> availableGroups = groupService.getGroups();

        User user = userService.findByEmail(principal.getName());
        Collection<Group> myGroups = groupService.getUserGroups(user);
        availableGroups.removeAll(myGroups);

        model.addAttribute("availableGroups", availableGroups);
        model.addAttribute("myGroups", myGroups);
        model.addAttribute("groupForm", new GroupForm());

        return "groups";
    }

    @RequestMapping(value = "/dodaj_grupe", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveGroup(@ModelAttribute("groupForm") @Valid GroupForm groupForm, BindingResult bindingResult, Principal principal, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "forms/add_group";
        }

        groupForm.setUser(userService.findByEmail(principal.getName()));
        Group savedGroup = groupService.save(groupForm);

        groupService.registerUser(userService.findByEmail(principal.getName()), savedGroup, UserRole.ADMINISTRATOR);

        redirectAttributes.addFlashAttribute("alertMessage", "Grupa została dodana");
        return "redirect:/grupy";
    }


    @RequestMapping(value = "/grupa/{id}", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String showGroup(@PathVariable("id") Long id, Model model) {
        Group group = groupService.getGroup(id);
        if (group == null)
            return "error_page";

        Collection<TaskList> taskList = taskService.getTaskLists(group);
        taskList.forEach(t -> t.setTasks(t.getTasks().stream().sorted(Comparator.comparing(Task::getDeadline).reversed()).collect(Collectors.toList())));
        TaskListForm taskListForm = new TaskListForm(group);

        GroupForm groupForm = new GroupForm();
        groupForm.setDescription(group.getDescription());
        groupForm.setName(group.getName());
        groupForm.setPassword(group.getPassword());

        model.addAttribute("groupForm", groupForm);
        model.addAttribute("taskListForm", taskListForm);
        model.addAttribute("taskList", taskList);
        model.addAttribute("group", group);

        return "group";
    }

    @RequestMapping(value = "/zapisz_do_grupy/{id}", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String registerUser(@PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes, Model model) {
        Group group = groupService.getGroup(id);
        if (group == null) {
            return "error_page";
        }

        if (!group.getPassword().isEmpty()) {
            model.addAttribute("group", group);
            model.addAttribute("passwordGroup", new PasswordGroup());
            return "forms/check_password_group";
        }

        User user = userService.findByEmail(principal.getName());

        if (groupService.isUserRegistered(user, group)) {
            return "error_page";
        }

        groupService.registerUser(user, group, UserRole.USER);
        redirectAttributes.addFlashAttribute("alertMessage", "Zostałeś zapisany do grupy");
        return "redirect:/grupy";
    }

    @RequestMapping(value = "/zapisz_do_grupy/{id}", method = RequestMethod.POST)
    @PreAuthorize("isFullyAuthenticated()")
    public String registerToGroupWithPassword(@PathVariable("id") Long id, @ModelAttribute("passwordGroup") @Valid PasswordGroup passwordGroup, BindingResult bindingResult, Principal principal, Model model, RedirectAttributes redirectAttributes) {

        Group group = groupService.getGroup(id);
        if (!group.getPassword().equals(passwordGroup.getPassword())) {
            bindingResult.addError(new FieldError("password", "password", "Hasło jest niepoprawne"));
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("group", group);
            redirectAttributes.addFlashAttribute("alertMessage", "Hasło jest niepoprawne");
            redirectAttributes.addFlashAttribute("type", "danger");
            return "redirect:/zapisz_do_grupy/" + id;
        }

        User user = userService.findByEmail(principal.getName());

        if (groupService.isUserRegistered(user, group)) {
            return "error_page";
        }

        groupService.registerUser(user, group, UserRole.USER);
        redirectAttributes.addFlashAttribute("alertMessage", "Zostałeś zapisany do grupy");
        return "redirect:/grupy";
    }

    @RequestMapping(value = "/wypisz_z_grupy/{id}", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String unregisterFromGroup(@PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes) {
        if (groupService.getGroup(id) == null) {
            return "error_page";
        }

        Group group = groupService.getGroup(id);
        User user = userService.findByEmail(principal.getName());

        if (!groupService.isUserRegistered(user, group)) {
            return "error_page";
        }

        if (groupService.getOwnGroups(user).contains(group)) {
            return "error_page";
        }

        groupService.unregisterUser(user, group);
        redirectAttributes.addFlashAttribute("alertMessage", "Zostałeś wypisany z grupy");
        return "redirect:/grupy";
    }

    @RequestMapping(value = "/edytuj_grupe/{groupId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String editGroup(@PathVariable("groupId") Long groupId, @ModelAttribute("groupForm") @Valid GroupForm groupForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Group group = groupService.getGroup(groupId);

        if (group == null) {
            return "error_page";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("groupId", groupId);
            return "forms/edit/edit_group";
        }

        group.setDescription(groupForm.getDescription());
        group.setName(groupForm.getName());
        group.setPassword(groupForm.getPassword());
        groupService.save(group);

        redirectAttributes.addFlashAttribute("alertMessage", "Grupa została zmieniona");
        return "redirect:/grupa/" + groupId;
    }

    @RequestMapping(value = "/usun_grupe/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteGroup(@PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes) {
        Group group = groupService.getGroup(id);

        if (group == null) {
            return "error_page";
        }

        groupService.delete(id);

        redirectAttributes.addFlashAttribute("alertMessage", "Grupa została usunięta");
        return "redirect:/grupy";
    }

}
