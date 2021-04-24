package com.ashutosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashutosh.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
