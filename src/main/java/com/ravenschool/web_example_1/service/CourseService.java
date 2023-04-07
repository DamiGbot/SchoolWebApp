package com.ravenschool.web_example_1.service;

import com.ravenschool.web_example_1.Model.Course;
import com.ravenschool.web_example_1.Model.Person;
import com.ravenschool.web_example_1.Repository.ICourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {

    private final ICourseRepository _courseRepository;
    private final PersonService _personService;

    public List<Course> getAllCourses(List<String> params, List<Boolean> direction) {
        List<Sort.Order> orders = new ArrayList<>();

        for (var idx = 0; idx < params.size(); idx++) {
            Sort.Direction sortDirection = Sort.Direction.ASC;
            if (direction.get(idx))
                sortDirection = Sort.Direction.DESC;

            Sort.Order newOrder = new Sort.Order(sortDirection, params.get(idx));
            orders.add(newOrder);
        }
        List<Course> courses =  _courseRepository.findAll(Sort.by(orders));
        // Add Extra Logic to be performed before returning the list.
        return courses;
    }

    public Course getCourse(int courseId) {
        Course course = null;
        Optional<Course> isCourse = _courseRepository.findById(courseId);
        if (isCourse.isPresent())
            course = isCourse.get();
        return course;
    }

    public boolean createCourse(Course course) {
        if (_courseRepository.findByName(course.getName()) != null)
            return false; // course already exists.
        String price = "$ " + course.getFees();
        course.setFees(price);
        Course newCourse = _courseRepository.save(course);

        return newCourse.getCourseId() > 0;
    }

    public boolean addNewStudent(Course course, Person person) {
        Set<Person> students = course.getPersons();
        if (students.contains(person))
            return false;

        students.add(person);
        Set<Course> currPersonCourses = person.getCourses();
        currPersonCourses.add(course);
        _personService.updateUser(person);
        return true;
    }

    public void deleteStudent(Course course, int personId) {
        Person currPerson = _personService.getUserId(personId);
        course.getPersons().remove(currPerson);
        currPerson.getCourses().remove(course);
        _personService.updateUser(currPerson);
    }
}
