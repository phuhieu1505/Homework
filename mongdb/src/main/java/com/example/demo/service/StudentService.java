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
import java.util.Optional;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public ResponseEntity<Student> addStudent(CreateStudentRequest student) throws Exception {
		if (student.getStu_id() == null || student.getStu_id().equals("") ||
				student.getStu_name() == null || student.getStu_name().equals("") ||
				student.getAge() == null || student.getAge() == 0||
		student.getClass_id() == null || student.getClass_id().equals("")

		) throw new Exception("Request invalid");
		return new ResponseEntity<>(studentRepository.save(
				Student.builder()
						.stu_id(student.getStu_id())
						.age(student.getAge())
						.stu_name(student.getStu_name())
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
					studentDataResponse.setStu_id(student.getStu_id());
					studentDataResponse.setStu_name(student.getStu_name());
					studentDataResponse.setAge(student.getAge());
					studentDataResponse.setClass_id(student.getClass_id());
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


	public ResponseEntity<StudentDataResponse> getStudentById(String stu_id) {
		Student student = studentRepository.getStudentById(stu_id);
		StudentDataResponse studentDataResponse = new StudentDataResponse();
		if (student != null) {
			studentDataResponse.setStu_id(student.getStu_id());
			studentDataResponse.setStu_name(student.getStu_name());
			studentDataResponse.setAge(student.getAge());
			studentDataResponse.setClass_id(student.getClass_id());
			return new ResponseEntity<>(studentDataResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Student> updateStudent(String stu_id, CreateStudentRequest student) {
		Optional<Student> studentOptional = studentRepository.findById(stu_id);

		if (studentOptional.isPresent()) {
			Student existingStudent = studentOptional.get();

			if (student.getStu_name() != null && !student.getStu_name().isEmpty()) {
				existingStudent.setStu_name(student.getStu_name());
			}
			if (student.getAge() != null && student.getAge() > 0) {
				existingStudent.setAge(student.getAge());
			}
			if (student.getClass_id() != null && !student.getClass_id().isEmpty()) {
				existingStudent.setClass_id(student.getClass_id());
			}

			return new ResponseEntity<>(studentRepository.save(existingStudent), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<StudentDataResponse> deleteStudent(String stu_id) {
		Optional<Student> student = studentRepository.findById(stu_id);
		StudentDataResponse studentDataResponse = new StudentDataResponse();
		if(student != null){
			Student existingStudent = student.get();
			studentRepository.deleteById(stu_id);
			studentDataResponse.setStu_id(existingStudent.getStu_id());
			studentDataResponse.setStu_name(existingStudent.getStu_name());
			studentDataResponse.setAge(existingStudent.getAge());
			studentDataResponse.setClass_id(existingStudent.getClass_id());
			return new ResponseEntity<>(studentDataResponse,HttpStatus.GONE);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
