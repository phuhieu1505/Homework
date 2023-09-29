package com.example.demo.service;

import com.example.demo.model.entity.Student;
import com.example.demo.model.http.CreateStudentRequest;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public ResponseEntity<Student> addStudent(CreateStudentRequest student) throws Exception {
		if (student.getName() == null || student.getName().equals("") ||
				student.getAge() == null || student.getAge() == 0
		) throw new Exception("Request invalid");
		return new ResponseEntity<>(studentRepository.save(
				Student.builder()
						.age(student.getAge())
						.name(student.getName())
						.build()
		), HttpStatus.CREATED);
	}

	public ResponseEntity<List<Student>> getStudent() {
		try {
			return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<Student> getStudent(String id) {
		return null;
	}


}
