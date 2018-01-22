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
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uwr.onlinejudge.server.models.Task;
import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.models.form.TestForm;
import uwr.onlinejudge.server.services.CompileService;
import uwr.onlinejudge.server.services.TaskService;
import uwr.onlinejudge.server.services.TestService;

import javax.validation.Valid;


@Controller
@RequestMapping
public class TestController {
    private TestService testService;
    private TaskService taskService;
    private CompileService compileService;

    @Autowired
    public TestController(TestService testService, TaskService taskService, CompileService compileService) {
        this.testService = testService;
        this.taskService = taskService;
        this.compileService = compileService;
    }

    @RequestMapping(value = "/testy/{id}/argumenty_wejsciowe", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String showInputArguments(@PathVariable("id") Long id, Model model) {
        Test test = testService.getTest(id);
        if (test == null)
            return "error_page";

        model.addAttribute("log", test.getInputArgument());
        model.addAttribute("title", "Argumenty wejściowe");

        return "log";
    }

    @RequestMapping(value = "/testy/{id}/argumenty_wyjsciowe", method = RequestMethod.GET)
    @PreAuthorize("isFullyAuthenticated()")
    public String showExpectedAnswer(@PathVariable("id") Long id, Model model) {
        Test test = testService.getTest(id);
        if (test == null)
            return "error_page";

        model.addAttribute("log", test.getExpectedAnswer());
        model.addAttribute("title", "Oczekiwana odpowiedź");

        return "log";
    }

    @RequestMapping(value = "/dodaj_test", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveTest(@ModelAttribute("testForm") @Valid TestForm testForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "forms/add_test";
        }

        long id = testService.save(testForm).getId();
        Test test = testService.getTest(id);
        return tryCompileLastSolutions(redirectAttributes, test);
    }

    @RequestMapping(value = "/dodaj_test_ponownie/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addTestAgain(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Test test = testService.getTest(id);

        if (test == null || test.isCompiled())
            return "error_page";

        return tryCompileLastSolutions(redirectAttributes, test);
    }

    private String tryCompileLastSolutions(RedirectAttributes redirectAttributes, Test test) {
        try {
            compileService.compileLastSolutions(test);
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("alertMessage", "Utracono połączenie z serwerem kompilatora. Spróbuj ponownie za jakiś czas.");
            redirectAttributes.addFlashAttribute("type", "alert-danger");
            return "redirect:/zadanie/" + test.getTask().getId();
        }
        test.setCompiled(true);
        testService.save(test);
        redirectAttributes.addFlashAttribute("alertMessage", "Test został dodany");
        return "redirect:/zadanie/" + test.getTask().getId();
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
