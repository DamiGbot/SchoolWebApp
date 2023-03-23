package com.ravenschool.web_example_1.service;

import com.ravenschool.web_example_1.Model.EazyClass;
import com.ravenschool.web_example_1.Repository.IEazyClassRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EazyClassService {

    private final IEazyClassRepository _IEazyClassRepository;

    public List<EazyClass> getAllClasses() {
        return _IEazyClassRepository.findAll();
    }

    public int addClass(EazyClass eazyClass, String classname) {
        if (_IEazyClassRepository.findByName(classname) != null)
            return -1;

        EazyClass newClass = _IEazyClassRepository.save(eazyClass);
        return newClass.getClassId();
    }

    public int deleteClass(int id) {
        Optional<EazyClass> eazyClass = _IEazyClassRepository.findById(id);
        if (eazyClass.isEmpty())
            return -1;

        _IEazyClassRepository.deleteById(id);
        return 0;
    }
}
