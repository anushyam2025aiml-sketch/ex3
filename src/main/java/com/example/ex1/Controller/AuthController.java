package com.example.ex1.Controller;

import com.example.ex1.entity.Studententity;
import com.example.ex1.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final StudentRepository studentRepository;

    public AuthController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody Studententity student) {

        // Check username already exists
        if (studentRepository.existsByUsername(student.getUsername())) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Username already exists");
        }

        // Save new student
        studentRepository.save(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Registration Successful");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Studententity student) {

        // Find username
        Optional<Studententity> existingStudent =
                studentRepository.findByUsername(
                        student.getUsername()
                );

        // Username does not exist
        if (existingStudent.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Username not found");
        }

        Studententity dbStudent = existingStudent.get();

        // Check password
        if (!dbStudent.getPassword()
                .equals(student.getPassword())) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Password");
        }

        // Login successful
        Map<String, String> response = new HashMap<>();

        response.put("message", "Login Successful");
        response.put("name", dbStudent.getName());

        return ResponseEntity.ok(response);
    }
}
