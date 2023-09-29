package com.example.demo.controller;

import com.example.demo.model.entity.Student;
import com.example.demo.model.http.CreateStudentRequest;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/student") // define api need define version of apis
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/post") // Re-name api, make it clean a
    public ResponseEntity<Student> addStudentInfo(@RequestBody CreateStudentRequest student) throws Exception {
        return studentService.addStudent(student);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Student>> getStudent(){
        return studentService.getStudent();
    }

    @GetMapping("/get") // TODO : update logic
    public ResponseEntity<Student> getStudent(@RequestParam(required = true) String id){
        return studentService.getStudent(id);
    }

}

