package com.ashutosh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashutosh.exception.StudentNotFoundException;
import com.ashutosh.model.Student;
import com.ashutosh.repository.StudentRepository;


@RestController
@RequestMapping("myapi")
@CrossOrigin(origins = "http://10.0.0.180:3000")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("students")	
	public List<Student> getStudent(){
		return studentRepository.findAll();
	}
	
	@PostMapping("students")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@GetMapping("students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable int id) {
		Student student = studentRepository.findById(id).orElseThrow(
				() -> new StudentNotFoundException("The student with id: " + id + " is not found in the db"));
		return ResponseEntity.ok(student);
	}
	
	@PutMapping("students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestBody Student stu) {
		Student student = studentRepository.findById(id).orElseThrow(
				() -> new StudentNotFoundException("The student with id: " + id + " is not found in the db"));
		
		student.setFirstName(stu.getFirstName());
		student.setLastName(stu.getLastName());
		student.setEmailId(stu.getEmailId());
		
		Student updatedStudent = studentRepository.save(student);
		return ResponseEntity.ok(updatedStudent);
	}
	
	@DeleteMapping("students/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteStudent(@PathVariable int id) {
		Student student = studentRepository.findById(id).orElseThrow(
				() -> new StudentNotFoundException("The student with id: " + id + " is not found in the db"));
		studentRepository.delete(student);
		
		Map<String,Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(map);
		
	}
}
