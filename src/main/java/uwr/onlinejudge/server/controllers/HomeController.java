package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uwr.onlinejudge.server.models.form.UserForm;
import uwr.onlinejudge.server.services.UserService;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class HomeController implements ErrorController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserForm());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult) {

        if( userService.findByEmail(userForm.getEmail()) != null ) {
            bindingResult.addError(new FieldError("email", "email", userForm.getEmail(), true, null, null,"Email zajęty"));
        }
        if( userForm.getPassword().compareTo(userForm.getPasswordConfirm()) != 0) {
            bindingResult.addError(new FieldError("password", "password", "Hasła nie są identyczne"));
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Twój email lub hasło są niepoprawne.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) {
        model.addAttribute("user", principal);
        return "index";
    }

    @RequestMapping(value = "/error")
    public String error() {
        return "error_page";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
