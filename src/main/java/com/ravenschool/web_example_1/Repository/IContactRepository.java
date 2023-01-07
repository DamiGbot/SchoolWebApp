package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Contact;

import java.util.List;

public interface IContactRepository {

    boolean saveContactMsg(Contact contact);
    List<Contact> findMsgsWithStatus(String status);
    boolean updateMsgStatus(int id, String status, String name);
}
