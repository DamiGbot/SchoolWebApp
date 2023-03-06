package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Helper.ModelValidation;
import com.ravenschool.web_example_1.Model.Contact;
import com.ravenschool.web_example_1.service.ContactService;
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

@Controller
@RequestMapping(value = {"/contact"})
@AllArgsConstructor
@Slf4j
public class ContactController {
    private final ContactService _contactService;
    private final ModelValidation<Contact> _modelValidation;

    @RequestMapping(value = {""})
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @PostMapping(value = {"/saveMsg"})
    public String SaveMessage(@Valid Contact contact, Errors errors) {
        if (errors.hasErrors())
            return _modelValidation.modelErrorPage(errors, "contact.html", "Contact form");


        _contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }

    @GetMapping(value = {"/displayMessages"})
    public ModelAndView displayMessages(Model model) {
        List<Contact> contactMsgs = _contactService.findMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs", contactMsgs);
        return modelAndView;
    }

    @GetMapping(value = {"/closeMsg"})
    public String closeAMessage(@RequestParam(name = "id") int contactId) {
        _contactService.updateMsgStatus(contactId);
        return "redirect:/contact/displayMessages";
    }
}
