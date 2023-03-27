package com.ravenschool.web_example_1.service;

import com.ravenschool.web_example_1.Model.EazyClass;
import com.ravenschool.web_example_1.Model.Person;
import com.ravenschool.web_example_1.Repository.IEazyClassRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EazyClassService {

    private final IEazyClassRepository _IEazyClassRepository;
    private final PersonService _personService;

    public List<EazyClass> getAllClasses() {
        return _IEazyClassRepository.findAll();
    }

    public int addClass(EazyClass eazyClass, String classname) {
        if (_IEazyClassRepository.findByName(classname) != null)
            return -1;

        EazyClass newClass = _IEazyClassRepository.save(eazyClass);
        return newClass.getClassId();
    }

    public EazyClass getClass(int id) {
        Optional<EazyClass> currOptional = _IEazyClassRepository.findById(id);

        EazyClass currClass = null;
        if (currOptional.isPresent()){
            currClass = currOptional.get();
        }

        return currClass;
    }

    public EazyClass updateClass(int id) {
        return getClass(id);
    }

    public int deleteClass(int id) {
        Optional<EazyClass> eazyClass = _IEazyClassRepository.findById(id);
        // remove the person from the classes
        if (eazyClass.isEmpty())
            return -1;

        for (Person person : eazyClass.get().getPersons()) {
            person.setEazyClass(null);
            _personService.updateUser(person);
        }
        _IEazyClassRepository.deleteById(id);
        return 0;
    }
}
