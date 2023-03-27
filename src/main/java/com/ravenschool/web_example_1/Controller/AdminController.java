package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Helper.ModelValidation;
import com.ravenschool.web_example_1.Model.EazyClass;
import com.ravenschool.web_example_1.Model.Person;
import com.ravenschool.web_example_1.service.EazyClassService;
import com.ravenschool.web_example_1.service.PersonService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping(value = {"/admin"})
public class AdminController {

    private final ModelValidation<EazyClass> _modelValidation;
    private final EazyClassService _eazyClassService;
    private final PersonService _personService;

//    @GetMapping(value = {"/courses"})
//    public ModelAndView courses() {
//
//    }

    @GetMapping(value = {"/classes"})
    public ModelAndView classes(Model model) {
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("eazyClass", new EazyClass());
        List<EazyClass> eazyClasses = _eazyClassService.getAllClasses();
        modelAndView.addObject("eazyClasses", eazyClasses);
        return modelAndView;
    }

    @PostMapping(value = {"/addNewClass"})
    public String addNewClass(@Valid EazyClass eazyClass, Errors errors) {
        if (errors.hasErrors())
            return _modelValidation.modelErrorPage(errors, "classes.html", "Add new Class Form");

        String className = eazyClass.getName();
        className = className.substring(0, 1).toUpperCase() + className.substring(1).toLowerCase();
        eazyClass.setName(className);

        int value = _eazyClassService.addClass(eazyClass, className);
        if (value < 0) {
            log.error("The name already exists");
            // we can add the error to a model object and return to the view
        }

        return "redirect:/admin/classes";
    }

    @GetMapping(value = {"/displayStudents"})
    public ModelAndView viewStudents(@RequestParam("classId") int id, HttpSession httpSession,
                                     @RequestParam(value = "error", required = false) String error) {
        // eazyClass,
        ModelAndView modelAndView = new ModelAndView("students.html");
        EazyClass eazyClass = _eazyClassService.getClass(id);
        if (eazyClass == null) {
            log.error("The class doesn't exist!!!");
            // return error to the view
        }
        System.out.println(eazyClass);
        modelAndView.addObject("eazyClass", eazyClass);
        modelAndView.addObject("person", new Person());
        httpSession.setAttribute("class", eazyClass);

        String errorMessage = null;
        if (error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @GetMapping(value = {"/deleteClass"})
    public String deleteClass(@RequestParam int id) {
        int value = _eazyClassService.deleteClass(id);
        if (value < 0) {
            log.error("The id doesn't exist");
            // we can add the error to a model object and return to the view
        }

        return "redirect:/admin/classes";
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Person person, Errors errors, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        EazyClass eazyClass = (EazyClass) httpSession.getAttribute("class");
        Person currPerson = _personService.getUser(person.getEmail());

        if (currPerson == null || currPerson.getPersonId() < 0) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" +
                    eazyClass.getClassId()+"&error=true");
            return modelAndView;
        }

        currPerson.setEazyClass(eazyClass);
        _personService.updateUser(currPerson);
        Set<Person> listOfPerson = eazyClass.getPersons();
        listOfPerson.add(currPerson);
        eazyClass.setPersons(listOfPerson);
        _eazyClassService.updateClass(eazyClass.getClassId());

        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" +
                eazyClass.getClassId());

        return modelAndView;
    }

    @GetMapping("deleteStudent")
    public ModelAndView deleteStudent(@RequestParam("personId") int id, HttpSession httpSession) {
        EazyClass eazyClass = (EazyClass) httpSession.getAttribute("class");
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" +
                eazyClass.getClassId());

        Person currPerson = _personService.deleteClass(id);
        Set<Person> listOfPerson = eazyClass.getPersons();
        listOfPerson.remove(currPerson);
        eazyClass.setPersons(listOfPerson);
        EazyClass updateClass = _eazyClassService.updateClass(eazyClass.getClassId());
        httpSession.setAttribute("class", updateClass);

        return modelAndView;
    }
}
