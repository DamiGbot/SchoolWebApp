package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Helper.ModelValidation;
import com.ravenschool.web_example_1.Model.Contact;
import com.ravenschool.web_example_1.service.ContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = {"/displayMessages/page/{pageNum}"})
    public ModelAndView displayMessages(Model model,
                                        @PathVariable(name = "pageNum") int pageNum,
                                        @RequestParam(name = "sortField") String sortField,
                                        @RequestParam(name = "sortDir") String sortDir) {
        Page<Contact> msgPage = _contactService.findMsgsWithOpenStatus(pageNum, sortField, sortDir);
        List<Contact> contactMsgs = msgPage.getContent();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", msgPage.getTotalPages());
        model.addAttribute("totalMsgs", msgPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contactMsgs", contactMsgs);
        return modelAndView;
    }

    @GetMapping(value = {"/closeMsg"})
    public String closeAMessage(@RequestParam(name = "id") int contactId) {
        _contactService.updateMsgStatus(contactId);
        return "redirect:/contact/displayMessages/page/1?sortField=name&sortDir=desc";
    }
}
