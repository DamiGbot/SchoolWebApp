package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Helper.ModelValidation;
import com.ravenschool.web_example_1.Model.EazyClass;
import com.ravenschool.web_example_1.service.EazyClassService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping(value = {"/admin"})
public class AdminController {

    private final ModelValidation<EazyClass> _modelValidation;
    private final EazyClassService _eazyClassService;

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

    @GetMapping(value = {"/deleteClass"})
    public String deleteClass(@RequestParam int id) {
        int value = _eazyClassService.deleteClass(id);
        if (value < 0) {
            log.error("The id doesn't exist");
            // we can add the error to a model object and return to the view
        }

        return "redirect:/admin/classes";
    }
}
