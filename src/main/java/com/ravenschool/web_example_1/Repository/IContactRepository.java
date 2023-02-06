package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactRepository extends CrudRepository<Contact, Integer> {

    // Derived Queries abstract methods
    List<Contact> findByStatus(String status);
}
