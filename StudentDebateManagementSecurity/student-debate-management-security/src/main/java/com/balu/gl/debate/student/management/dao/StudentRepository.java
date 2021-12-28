package com.balu.gl.debate.student.management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balu.gl.debate.student.management.entity.Student;

public interface  StudentRepository extends JpaRepository<Student, Integer>{

	List<Student> findByFirstNameOrLastName(String firstName, String lastName);

}
