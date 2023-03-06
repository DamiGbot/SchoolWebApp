package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Helper.ModelValidation;
import com.ravenschool.web_example_1.Model.Person;
import com.ravenschool.web_example_1.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(value = {"/public"})
public class PublicController {

    private final ModelValidation<Person> _modelValidation;
    private final PersonService _personService;

    @GetMapping(value = "/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @PostMapping(value = "/createUser")
    public String createUser(@Valid Person person, Errors errors) {
        if (errors.hasErrors() )
            return _modelValidation.modelErrorPage(errors, "register.html", "Create user");

        int newUserId = _personService.createUser(person);
        if (newUserId == 0) return "register.html";

        return "redirect:/login?register=true";
    }
}
