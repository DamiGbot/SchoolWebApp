package com.ravenschool.web_example_1.service;

import com.ravenschool.web_example_1.Constants.IEazySchoolConstants;
import com.ravenschool.web_example_1.Model.Person;
import com.ravenschool.web_example_1.Model.Role;
import com.ravenschool.web_example_1.Repository.IPersonRepository;
import com.ravenschool.web_example_1.Repository.IRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {

    private final IPersonRepository _personRepository;
    private final IRoleRepository _roleRepository;
    private final PasswordEncoder _passwordEncoder;

    public int createUser(Person person) {
        Role role = _roleRepository.findByRoleName(IEazySchoolConstants.STUDENT_ROLE);
        person.setRole(role);
        person.setPwd(_passwordEncoder.encode(person.getPwd()));

        Person newUser = _personRepository.save(person);

        return newUser.getPersonId();
    }

    public Person getUser(String email) {
        return _personRepository.getByEmail(email);
    }
}
