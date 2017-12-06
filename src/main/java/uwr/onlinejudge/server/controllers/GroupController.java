package uwr.onlinejudge.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uwr.onlinejudge.server.services.GroupService;

@Controller
@RequestMapping("/grupy")
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Example title");
        return "index";
    }

    @RequestMapping(value = "/dodaj_grupe", method = RequestMethod.GET)
    public String addGroup(Model model) {
        model.addAttribute("title", "Example title");
        return "index";
    }

    @RequestMapping(value = "/grupa", method = RequestMethod.GET)
    public String showGroup(Model model) {
        model.addAttribute("title", "Example title");
        return "indemovex";
    }
}
