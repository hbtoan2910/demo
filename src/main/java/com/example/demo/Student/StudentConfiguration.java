package com.example.demo.Student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfiguration {
	//Usually, we use this CommandLineRunner to perform startup tasks like user or database initialization, seeding, or other startup activities.
	//There are several ways to deploy CommandLineRunner
	
	//It’s also common for the developer to annotate the CommandLineRunner as a @Bean; Spring will also run the code during the startup of the Spring application.
	@Bean
	CommandLineRunner startup(StudentRepository studentRepository) {		
		
		return args -> {	
			
				Student ryan = new Student(
						"Ryan Huynh",
						"hbtoan2910@gmail.com",
						LocalDate.of(1985, 10, 29)
					);
				Student kent = new Student(
						"Kent Nguyen",
						"tunginusa@hotmail.com",
						LocalDate.of(1983, 05, 16)
					);
				Student maria = new Student(
						"Maria Leung",
						"maria@hotmail.com",
						LocalDate.of(1980, 11, 16)
					);
				
		studentRepository.saveAll(List.of(ryan, kent, maria));
		};
	}
}
