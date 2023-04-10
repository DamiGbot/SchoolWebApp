package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer> {

    // Derived Queries abstract methods
    List<Contact> findByStatus(String status);
//    @Query("Select c from Contact c where c.status = :status")
    @Query(value = "Select * from contact_msg where status = :status", nativeQuery = true)
    Page<Contact> findByStatus(String status, Pageable pageable);

//    @Transactional
//    @Modifying
//    @Query(value = "update contact_msg set status = :status, updated_at = Now(), updated_by = 'admin' where contact_id = :id", nativeQuery = true)
//    int updateStatusById(String status, int id);

    @Transactional
    @Modifying
    int updateStatusById(String status, int id);
}
