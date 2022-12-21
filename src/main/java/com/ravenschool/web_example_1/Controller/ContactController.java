package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Model.Contact;
import com.ravenschool.web_example_1.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
@RequestMapping(value = {"/contact"})
public class ContactController {

    private final Logger log = Logger.getLogger(ContactController.class.getName());
    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = {""})
    public String displayContactPage() {
        return "contact.html";
    }

    @PostMapping(value = {"/saveMsg"})
    public ModelAndView SaveMessage(Contact contact) {
        contactService.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }
}
