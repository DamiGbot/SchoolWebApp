package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.EazyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEazyClassRepository extends JpaRepository<EazyClass, Integer> {
    EazyClass findByName(String name);
}
