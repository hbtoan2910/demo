package com.example.demo.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	//If use @Query like below, you can name function name by whatever you want
	//But if not using @Query, must follow conventional to name function	
	//@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Optional<Student> findStudentByEmail(String email);
}
