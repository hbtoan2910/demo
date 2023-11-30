package com.example.demo.Teacher;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/api/v1/teacher")
public class TeacherController {
	
	private final TeacherService teacherService;	
	
	@Autowired //Constructor based - Dependency injection
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
//	@Autowired //Setter based - Dependency injection
//	public void setteacherService(teacherService teacherService) {
//		this.teacherService = teacherService;
//	}
	
	@GetMapping
	public List<Teacher> getteachers() {
		return teacherService.getTeachers();
	}
	
	@PostMapping
	public Teacher registerNewteacher(@RequestBody Teacher teacher) {
		return teacherService.addNewteacher(teacher);
	}
	
	@DeleteMapping(path = "{teacherId}")
	public void deleteteacher(@PathVariable("teacherId") Long teacherId) {
		teacherService.deleteteacher(teacherId);
	}
}
