package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Helper.ModelValidation;
import com.ravenschool.web_example_1.Model.Person;
import com.ravenschool.web_example_1.Model.Profile;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.security.core.Authentication;
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
    public String updateProfile(@Valid Profile profile, Errors errors, Authentication authentication) {
        if (errors.hasErrors())
            return _modelValidation.modelErrorPage(errors, "/profile/displayProfile.html", "Profile form");

        System.out.println(profile);

        return "redirect:/displayProfile";
    }
}

