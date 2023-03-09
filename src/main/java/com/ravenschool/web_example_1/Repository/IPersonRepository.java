package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Integer> {
    Person getByEmail(String email);
}
