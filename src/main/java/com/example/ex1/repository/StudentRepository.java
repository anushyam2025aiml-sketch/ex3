package com.example.ex1.repository;

import com.example.ex1.entity.Studententity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Studententity, Long> {

    boolean existsByUsername(String username);

    Optional<Studententity> findByUsername(String username);
}
