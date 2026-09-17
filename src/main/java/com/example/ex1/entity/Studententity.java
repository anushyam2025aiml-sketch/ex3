package com.example.ex1.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Studententity {
    @Id
    private int id;
    private String name;
    private String department;
    private int age;

    //default constructor
    public Studententity() {

    }

    // Parameterized constructor
    public Studententity(int id, String name, String department, int age) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.age = age;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

