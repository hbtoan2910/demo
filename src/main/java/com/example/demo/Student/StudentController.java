package com.example.demo.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {
	
	private final StudentService studentService;	
	
	@Autowired //Constructor based - Dependency injection
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
//	@Autowired //Setter based - Dependency injection
//	public void setStudentService(StudentService studentService) {
//		this.studentService = studentService;
//	}
	
	@GetMapping(value = "/greet")
	public GreetResponse greet() {
		GreetResponse gr = new GreetResponse(
																		"HELLO RYAN BÉO Ú", 
																		List.of("Java", "Javascript", "Python", "Php", "C#"), 
																		new Person("Kent Nguyen", 38, 55000)
	);
		System.out.println(gr);
		return gr;
	}
	
	record Person(String name, int age, double savings) { };
	record GreetResponse(
										 String greet,
										 List<String> favProgrammingLanguages,
										 Person person
	) {	};
	
	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping
	public Student registerNewStudent(@RequestBody Student student) {
		return studentService.addNewStudent(student);
	}
	
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}
	
	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId") Long studentId, 
											@RequestParam(required = false) String name, 
											@RequestParam(required = false) String email) {
		
		studentService.editStudent(studentId, name, email);
	}
}
