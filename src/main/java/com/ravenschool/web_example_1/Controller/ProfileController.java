package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Helper.ModelValidation;
import com.ravenschool.web_example_1.Model.Address;
import com.ravenschool.web_example_1.Model.Person;
import com.ravenschool.web_example_1.Model.Profile;
import com.ravenschool.web_example_1.service.PersonService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping(value = {"/profile"})
public class ProfileController {

    private final Mapper _mapper;
    private final ModelValidation<Profile> _modelValidation;
    private final PersonService _personService;

    @GetMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, HttpSession session) {
        Profile profile = new Profile();
        Person person = (Person) session.getAttribute("currPerson");
        _mapper.map(person, profile);

        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid Profile profile, Errors errors, HttpSession session) {
        if (errors.hasErrors())
            return _modelValidation.modelErrorPage(errors, "profile.html", "Profile form");
        Person person = (Person) session.getAttribute("currPerson");
        Address personAddress = new Address();
        _mapper.map(profile, personAddress);
        person.setAddress(personAddress);
        _personService.updateUser(person);
        session.setAttribute("currPerson", person);
        return "redirect:/profile/displayProfile";
    }
}

