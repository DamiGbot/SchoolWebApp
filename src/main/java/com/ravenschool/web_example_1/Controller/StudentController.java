package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Model.Person;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping(value = "/student")
@AllArgsConstructor
public class StudentController {

    @GetMapping(value = "/displayCourses")
    public ModelAndView displayCourses(HttpSession session) {
        Person person = (Person)session.getAttribute("currPerson");
        ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
        modelAndView.addObject("person", person);
        return modelAndView;
    }
}
