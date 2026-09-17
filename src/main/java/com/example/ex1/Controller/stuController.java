package com.example.ex1.Controller;
import com.example.ex1.entity.Studententity;
import com.example.ex1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/students")
public class stuController {

    @Autowired
    private StudentRepository studentRepository;

    // 1. Get all students
    @GetMapping
    public List<Studententity> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/hello")
    public String hello(){
        return "welcome to spring class";
    }

    // 2. Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Studententity> getStudentById(@PathVariable Long id) {
        Optional<Studententity> student = studentRepository.findById(id);
        return student.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Create a new student
    @PostMapping
    public ResponseEntity<Studententity> createStudent(@RequestBody Studententity student) {
        Studententity savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // 4. Update an existing student
    @PutMapping("/{id}")
    public ResponseEntity<Studententity> updateStudent(@PathVariable Long id, 
                                                       @RequestBody Studententity studentDetails) {
        Optional<Studententity> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            Studententity student = optionalStudent.get();
            student.setName(studentDetails.getName());
            student.setDepartment(studentDetails.getDepartment());
            student.setAge(studentDetails.getAge());

            Studententity updatedStudent = studentRepository.save(student);
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}