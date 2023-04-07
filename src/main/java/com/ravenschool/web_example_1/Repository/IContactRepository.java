package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer> {

    // Derived Queries abstract methods
    List<Contact> findByStatus(String status);
    Page<Contact> findByStatus(String status, Pageable pageable);
}
