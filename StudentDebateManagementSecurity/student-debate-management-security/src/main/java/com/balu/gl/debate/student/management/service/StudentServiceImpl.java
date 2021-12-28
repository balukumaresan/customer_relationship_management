package com.balu.gl.debate.student.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balu.gl.debate.student.management.dao.StudentRepository;
import com.balu.gl.debate.student.management.entity.Student;
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Student findById(int studentId) {
		Optional<Student> opt = studentRepository.findById(studentId);
		return opt.get();
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);

	}

	@Override
	public void deleteById(int studentId) {
		studentRepository.deleteById(studentId);

	}

	@Override
	public List<Student> searchBy(String firstName, String lastName) {
		// TODO Auto-generated method stub
		List <Student> books = studentRepository.findByFirstNameOrLastName(firstName, lastName);
		return books;
	}
}
