package com.example.demo.service;

import com.example.demo.model.entity.Student;
import com.example.demo.model.http.CreateStudentRequest;
import com.example.demo.model.http.StudentDataResponse;
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

	public ResponseEntity<List<StudentDataResponse>> getStudent() {
		try {
			List<Student> studentList = studentRepository.findAll();
			if (!studentList.isEmpty()) {
				List<StudentDataResponse> studentDataResponseList = new ArrayList<>();

				// Sao chép dữ liệu từ danh sách Student sang danh sách StudentDataResponse
				for (Student student : studentList) {
					StudentDataResponse studentDataResponse = new StudentDataResponse();
					studentDataResponse.setId((student.getId()));
					studentDataResponse.setName(student.getName());
					studentDataResponse.setAge(student.getAge());
					studentDataResponseList.add(studentDataResponse);
				}

				return new ResponseEntity<>(studentDataResponseList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	public ResponseEntity<StudentDataResponse> getStudent(int id) {
		List<Student> studentList = new ArrayList<>();
		

		return null;
	}


}
