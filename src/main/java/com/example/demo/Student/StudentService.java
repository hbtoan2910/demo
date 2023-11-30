package com.example.demo.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudents() {		
//		LocalDate birthDate = LocalDate.of(1985, 10, 29);
//		LocalDate currentDate = LocalDate.now();
//		Period age = Period.between(birthDate, currentDate);
//		
//		return List.of(new Student(
//					1L,
//					"Ryan Huynh",
//					"hbtoan2910@gmail.com",
//					birthDate,
//					age.getYears()
//				));
		return studentRepository.findAll();
	}
	
	public Student addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());		
		if (studentByEmail.isPresent()) {
			throw new IllegalStateException("student email taken");
		}
		//System.out.println(student);
		return studentRepository.save(student); 
	}
	
	public void deleteStudent(Long studentId) {
		boolean exist = studentRepository.existsById(studentId);
		if (!exist) {
			throw new IllegalStateException("Student with ID: " + studentId + " does not exist." );
		}
		studentRepository.deleteById(studentId);
	}
	
	@Transactional 
	//if we set annotation @Transactional for this method, we don't need to use Save method from StudentRepository that extends from JPARepository to save data to db
	public void editStudent(Long studentId, String name, String email) {
		
		Student student = studentRepository.findById(studentId)
															.orElseThrow( () -> new IllegalStateException("Student with ID: " + studentId + " does not exist.") );
		
		if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
			student.setName(name);
			//studentRepository.save(student);
		}
		
		if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);	
			if (studentByEmail.isPresent()) {
				throw new IllegalStateException("student email taken");
			}
			student.setEmail(email);
			//studentRepository.save(student);
		}
	}
}
