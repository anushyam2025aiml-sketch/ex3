package com.example.ex1.repository;
import com.example.ex1.entity.Studententity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository 
public interface StudentRepository extends JpaRepository<Studententity,Integer> {
 
}
