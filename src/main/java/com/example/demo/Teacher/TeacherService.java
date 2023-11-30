package com.example.demo.Teacher;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
	
	private final TeacherRepository teacherRepository;
	
	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}
	
	public List<Teacher> getTeachers() {		
//		LocalDate birthDate = LocalDate.of(1985, 10, 29);
//		LocalDate currentDate = LocalDate.now();
//		Period age = Period.between(birthDate, currentDate);
//		
//		return List.of(new teacher(
//					1L,
//					"Ryan Huynh",
//					"hbtoan2910@gmail.com",
//					birthDate,
//					age.getYears()
//				));
		return teacherRepository.findAll();
	}
	
	public Teacher addNewteacher(Teacher teacher) {
		Optional<Teacher> teacherByEmail = teacherRepository.findSomethingByEmail(teacher.getEmail());		
		if (teacherByEmail.isPresent()) {
			throw new IllegalStateException("teacher email taken");
		}
		//System.out.println(teacher);
		return teacherRepository.save(teacher); 
	}
	
	public void deleteteacher(Long teacherId) {
		boolean exist = teacherRepository.existsById(teacherId);
		if (!exist) {
			throw new IllegalStateException("teacher with ID: " + teacherId + " does not exist." );
		}
		teacherRepository.deleteById(teacherId);
	}
}
