package com.example.demo.controller;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.repository.StudentRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Student;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id){
		Student student=studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student does not exists with id:"+id));
		return ResponseEntity.ok(student);
	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails){
		Student student=studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student does not exists with id:"+id));
		
		student.setFirstName(studentDetails.getFirstName());
		student.setLastName(studentDetails.getLastName());
		student.setEmailId(studentDetails.getEmailId());
		student.setAddress(studentDetails.getAddress());
		student.setCity(studentDetails.getCity());
		student.setState(studentDetails.getState());
		student.setZipcode(studentDetails.getZipcode());
		student.setTelephone(studentDetails.getTelephone());
		student.setDate(studentDetails.getDate());
		student.setLikeMOst(studentDetails.getLikeMOst());
		student.setInterest(studentDetails.getInterest());
		student.setRecommend(studentDetails.getRecommend());
		Student updatedStudent= studentRepository.save(student);
		return ResponseEntity.ok(updatedStudent);
		
	}
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteStudent(@PathVariable Long id){
		Student student=studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student does not exists with id:"+id));
		studentRepository.delete(student);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
