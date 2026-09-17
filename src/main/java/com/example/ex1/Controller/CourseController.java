package com.example.ex1.Controller;

import com.example.ex1.entity.Course;
import com.example.ex1.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // 1. Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // 2. Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {

        Optional<Course> course = courseRepository.findById(id);

        return course.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Create a new course
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {

        Course savedCourse = courseRepository.save(course);

        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    // 4. Update an existing course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id,
            @RequestBody Course courseDetails) {

        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (optionalCourse.isPresent()) {

            Course course = optionalCourse.get();

            course.setCourseName(courseDetails.getCourseName());
            course.setDepartment(courseDetails.getDepartment());
            course.setDuration(courseDetails.getDuration());
            course.setFees(courseDetails.getFees());

            Course updatedCourse = courseRepository.save(course);

            return ResponseEntity.ok(updatedCourse);

        } else {

            return ResponseEntity.notFound().build();
        }
    }

    // 5. Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        if (courseRepository.existsById(id)) {

            courseRepository.deleteById(id);

            return ResponseEntity.noContent().build();

        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
