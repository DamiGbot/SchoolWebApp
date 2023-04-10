package com.ravenschool.web_example_1.service;

import com.ravenschool.web_example_1.Constants.IEazySchoolConstants;
import com.ravenschool.web_example_1.Model.Contact;
import com.ravenschool.web_example_1.Repository.IContactRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ContactService {

    private final IContactRepository _contactRepository;

    public boolean saveMessageDetails(Contact contact) {

        contact.setStatus(IEazySchoolConstants.OPEN);
        boolean isMessageSaved = saveToDb(contact);
        log.info(contact.toString());

        return isMessageSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending());
        Page<Contact> contactMsgs = _contactRepository.findByStatus(IEazySchoolConstants.OPEN, pageable);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId) {
        boolean isContactUpdated = _contactRepository.updateStatusById(IEazySchoolConstants.CLOSE, contactId) > 0;
        return isContactUpdated;
    }

    private boolean saveToDb(Contact contact) {
        Contact savedContact = _contactRepository.save(contact);

        return savedContact.getContactId() > 0 &&
                !savedContact.getMessage().isEmpty() && !savedContact.getName().isEmpty();
    }
}
