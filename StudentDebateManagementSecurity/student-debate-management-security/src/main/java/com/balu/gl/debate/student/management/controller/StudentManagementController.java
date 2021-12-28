package com.balu.gl.debate.student.management.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.balu.gl.debate.student.management.entity.Student;
import com.balu.gl.debate.student.management.service.StudentService;


@Controller
@RequestMapping("/student")
public class StudentManagementController {

	@Autowired
	private StudentService studentService; 
	
	@RequestMapping("/listStudent")
	public String listBooks(Model model) {
		
		List<Student> students = studentService.findAll();
		model.addAttribute("students", students);
		return "list-student";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		// create model attribute to bind form data
		Student student = new Student();

		model.addAttribute("student", student);

		return "student-form";
	}

	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int studentId,
			Model model) {

		Student student = studentService.findById(studentId);
		model.addAttribute("Book", student);

		return "student-form";			
	}


	@PostMapping("/save")
	public String saveBook(@RequestParam("studentId") int studentId,
			@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("course") String course, @RequestParam("country") String country) {

		Student student = null;
		if(studentId !=0)
		{
			student = studentService.findById(studentId);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setCountry(country);
			student.setCourse(course);
		}
		else
			student = new Student(firstName, lastName, course, country);
		studentService.save(student);


		// use a redirect to prevent duplicate submissions
		return "redirect:/student/listStudent";

	}

	

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theIstudentId) {

		// delete the Book
		studentService.deleteById(theIstudentId);

		// redirect to /Books/list
		return "redirect:/student/listStudent";

	}


	@RequestMapping("/search")
	public String search(@RequestParam("firstNme") String name,
			@RequestParam("lastName") String author,
			Model model) {

		if (name.trim().isEmpty() && author.trim().isEmpty()) {
			return "redirect:/student/listStudent";
		}
		else {
			// else, search by first name and last name
			List<Student> student =
					studentService.searchBy(name, author);

			// add to the spring model
			model.addAttribute("students", student);

			// send to list-Books
			return "list-student";
		}

	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
}
