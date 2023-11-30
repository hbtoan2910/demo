package com.example.demo.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
public class Student {
	
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_seq", allocationSize = 2, initialValue = 4 ) 
	/* NOTES: trong Hibernate, nên dùng GENERATIONTYPE là sequence thay vì auto / identity / table vì nó hỗ trợ BATCH INSERT
	Khi send request để tạo data cho table nào đầu tiên thì giá trị cột ID của table này (table A) sẽ là 4 (do initialValue = 4), sau đó nếu tiếp tục tạo data cho table này (table A), thì giá trị sẽ là 5, 6, 7, ...
	Nhưng nếu chỉ tạo data tới ID = 5 cho table này (table A), rồi lại tạo data cho 1 table khác (table B), thì ID của table khác này (table B) sẽ bắt đầu = 7 (do allocationSize = 2), sau đó nếu tiếp tục tạo data cho table này (table B), thì giá trị sẽ là 8, 9, 10, ...
	Mục đích chính là ID của các bảng sẽ KHÔNG GIỐNG NHAU.
	*/
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    
	/* Old way - deprecated
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq-gen"
    )	
	@GenericGenerator(name="seq-gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		      parameters = {
		    	        @Parameter(name = "sequence_name", value = "student_sequence"),
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
	public Student() {
	}
	public Student(Long id, String name, String email, LocalDate dob) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}
	
	public Student(String name, String email, LocalDate dob) {
		this.name = name;
		this.email = email;
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age + "]";
	}	
	
}
