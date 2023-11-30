package com.example.demo.Teacher;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Teacher {
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_seq", allocationSize = 2, initialValue = 4 )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    
	/* Old way - deprecated
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq-gen"
    )	
	@GenericGenerator(name="seq-gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		      parameters = {
		    	        @Parameter(name = "sequence_name", value = "Teacher_sequence"),
		    	        @Parameter(name = "initial_value", value = "100"),
		    	        @Parameter(name = "increment_size", value = "50")
		    	        })
	*/
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "dob")
	private LocalDate dob;
	
	@Column(name = "age")
	@Transient
	private Integer age;
	
	@Column(name = "professional_field")
	private  final String professional = "SCIENCE" ;	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Integer getAge() {
		return Period.between(dob, LocalDate.now()).getYears();
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getProfessional() {
		return professional;
	}
	public Teacher() {
	}
	public Teacher(Long id, String name, String email, LocalDate dob) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}
	
	public Teacher(String name, String email, LocalDate dob) {
		this.name = name;
		this.email = email;
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age + "]";
	}	
	
}
