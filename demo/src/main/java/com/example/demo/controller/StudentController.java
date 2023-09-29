package com.example.demo.controller;

import com.example.demo.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/post")
    public ResponseEntity<Student> addStudentInfo(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Student>> getStudent(){
        return studentService.getStudent();
    }

}

