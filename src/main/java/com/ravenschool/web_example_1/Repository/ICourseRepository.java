package com.ravenschool.web_example_1.Repository;

import com.ravenschool.web_example_1.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer> {
    Course findByName(String name);

    // Static Sorting 
//    List<Course> findByOrderByName();
//    List<Course> findByOrderByNameDesc();
}
