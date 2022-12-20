package com.ravenschool.web_example_1.service;

import com.ravenschool.web_example_1.Model.Contact;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ContactService {

    private final Logger log = Logger.getLogger(ContactService.class.getName());

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        log.info(contact.toString());
        return isSaved;
    }
}
