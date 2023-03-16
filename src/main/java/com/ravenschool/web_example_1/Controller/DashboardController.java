package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Model.Person;
import com.ravenschool.web_example_1.service.PersonService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class DashboardController {
    private final PersonService _personService;

    @GetMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {
        Person person = _personService.getUser(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", String.valueOf(authentication.getAuthorities()));
        session.setAttribute("currPerson", person);
        return "dashboard.html";
    }

}