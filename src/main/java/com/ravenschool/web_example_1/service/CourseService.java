package com.ravenschool.web_example_1.service;

import com.ravenschool.web_example_1.Model.Course;
import com.ravenschool.web_example_1.Model.Person;
import com.ravenschool.web_example_1.Repository.ICourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {

    private final ICourseRepository _courseRepository;
    private final PersonService _personService;

    public List<Course> getCourses() {
        List<Course> courses = _courseRepository.findAll();
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

    public boolean addNewStudents(int id, Person person) {
        Optional<Course> isCourse = _courseRepository.findById(id);
        if (isCourse.isPresent()) {
            Course course = isCourse.get();
            Set<Person> students = course.getPersons();
            Person currPerson = _personService.getUser(person.getEmail());
            if (currPerson == null || students.contains(currPerson))
                return false;
            students.add(currPerson);
            course.setPersons(students);
            _courseRepository.save(course);
            Set<Course> currPersonCourses = currPerson.getCourses();
            currPersonCourses.add(course);
            currPerson.setCourses(currPersonCourses);
            _personService.updateUser(currPerson);
        }

        return true;
    }
}
