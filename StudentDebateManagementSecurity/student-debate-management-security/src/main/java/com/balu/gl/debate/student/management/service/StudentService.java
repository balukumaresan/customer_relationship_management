package com.balu.gl.debate.student.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.balu.gl.debate.student.management.entity.Student;

@Service
public interface StudentService {

	public List<Student> findAll();
	public Student findById(int studentId);
	public void save(Student student);
	public void deleteById(int studentId);
	public List<Student> searchBy(String firstName, String lastName);
}
