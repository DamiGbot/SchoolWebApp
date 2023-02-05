package com.ravenschool.web_example_1.service;

import com.ravenschool.web_example_1.Constants.IEazySchoolConstants;
import com.ravenschool.web_example_1.Model.Contact;
import com.ravenschool.web_example_1.Repository.IContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    private final IContactRepository _contactRepository;

    public ContactService(IContactRepository contactRepository) {
        this._contactRepository = contactRepository;
    }

    public boolean saveMessageDetails(Contact contact) {

        contact.setStatus(IEazySchoolConstants.OPEN);
        contact.setCreated_by(IEazySchoolConstants.ANONYMOUS);
        contact.setCreated_at(LocalDateTime.now());
        boolean isMessageSaved = saveToDb(contact);
        log.info(contact.toString());

        return isMessageSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = _contactRepository.findByStatus(IEazySchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String updatedBy) {
        Optional<Contact> contact = _contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(IEazySchoolConstants.CLOSE);
            contact1.setUpdated_by(updatedBy);
            contact1.setUpdated_at(LocalDateTime.now());
        });

        boolean isContactUpdated = false;
        if (contact.isPresent())
            isContactUpdated = saveToDb(contact.get());

        return isContactUpdated;
    }

    private boolean saveToDb(Contact contact) {
        Contact savedContact = _contactRepository.save(contact);

        return savedContact.getContactId() > 0 &&
                !savedContact.getMessage().isEmpty() && !savedContact.getName().isEmpty();
    }
}
