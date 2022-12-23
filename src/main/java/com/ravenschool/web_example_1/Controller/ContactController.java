package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Model.Contact;
import com.ravenschool.web_example_1.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/contact"})
@Slf4j
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = {""})
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @PostMapping(value = {"/saveMsg"})
    public String SaveMessage(@Valid Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to: " + errors);
            return "contact.html";
        }

        contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }
}
