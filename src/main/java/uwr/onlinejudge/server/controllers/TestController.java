package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.models.form.TestForm;
import uwr.onlinejudge.server.services.TaskService;
import uwr.onlinejudge.server.services.TestService;
import uwr.onlinejudge.server.util.breadcrumbs.BreadCrumbs;
import uwr.onlinejudge.server.util.breadcrumbs.Link;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping
public class TestController {
    TestService testService;
    TaskService taskService;
    BreadCrumbs breadCrumbs;

    @Autowired
    public TestController(TestService testService, TaskService taskService, BreadCrumbs breadCrumbs) {
        this.testService = testService;
        this.taskService = taskService;
        this.breadCrumbs = breadCrumbs;
    }

    @RequestMapping(value = "/testy/{id}/argumenty_wejsciowe", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String showInputArguments(@PathVariable("id") Long id, Model model, HttpServletRequest request, HttpSession session) {
        Test test = testService.getTest(id);
        if (test == null)
            return "error_page";

        model.addAttribute("log", test.getInputArgument());
        model.addAttribute("title", "Argumenty wejściowe");
        model.addAttribute("breadcrumb", true);

        Link link = new Link("Argumenty wejściowe", "Grupy", "Zadanie", "Argumenty wejściowe");
        breadCrumbs.add(request, session, link);

        return "log";
    }

    @RequestMapping(value = "/testy/{id}/argumenty_wyjsciowe", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String showExpectedAnswer(@PathVariable("id") Long id, Model model, HttpServletRequest request, HttpSession session) {
        Test test = testService.getTest(id);
        if (test == null)
            return "error_page";

        model.addAttribute("log", test.getExpectedAnswer());
        model.addAttribute("title", "Oczekiwana odpowiedź");

        Link link = new Link("Argumenty wyjściowe", "Grupy", "Zadanie", "Argumenty wyjściowe");
        breadCrumbs.add(request, session, link);

        return "log";
    }

    @RequestMapping(value = "/dodaj_test/{taskId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addTest(@PathVariable("taskId") Long taskId, Model model, HttpServletRequest request, HttpSession session) {
        Task task = taskService.getTask(taskId);
        TestForm testForm = new TestForm();

        if (task == null)
            return "error_page";

        model.addAttribute("task", task);
        model.addAttribute("test", testForm);
        model.addAttribute("breadcrumb", true);

        Link link = new Link("Dodaj test", "Grupy", "Zadanie", "Dodaj test");
        breadCrumbs.add(request, session, link);

        return "forms/add_test";
    }

    @RequestMapping(value = "/dodaj_test/{taskId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveTest(@PathVariable("taskId") Long taskId, @ModelAttribute("test") @Valid TestForm testForm, RedirectAttributes redirectAttributes) {
        Task task = taskService.getTask(taskId);

        if (task == null)
            return "error_page";

        testForm.setTask(task);
        testService.save(testForm);

        redirectAttributes.addFlashAttribute("alertMessage", "Test został dodany");
        return "redirect:/zadanie/" + task.getId();
    }

    @RequestMapping(value = "/usun_test/{taskId}/{testId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteTest(@PathVariable("taskId") Long taskId, @PathVariable("testId") Long testId, RedirectAttributes redirectAttributes) {
        Task task = taskService.getTask(taskId);
        Test test = testService.getTest(testId);

        if (test == null || task == null)
            return "error_page";

        testService.deleteTest(testId);
        redirectAttributes.addFlashAttribute("alertMessage", "Test został usunięty");
        return "redirect:/zadanie/" + task.getId();
    }
}
