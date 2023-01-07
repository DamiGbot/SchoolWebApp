package com.ravenschool.web_example_1.service;

import com.ravenschool.web_example_1.Constants.IEazySchoolConstants;
import com.ravenschool.web_example_1.Model.Contact;
import com.ravenschool.web_example_1.Repository.IContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        boolean isMessageSaved = _contactRepository.saveContactMsg(contact);

        log.info(contact.toString());
        return isMessageSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = _contactRepository.findMsgsWithStatus(IEazySchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int id, String name) {
        return _contactRepository.updateMsgStatus(id, IEazySchoolConstants.CLOSE, name);
    }
}
