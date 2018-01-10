package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.services.TestService;

@Controller
@RequestMapping
public class TestController {
    TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
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
}
