package com.example.demo.Controller;

import com.example.demo.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/post")
    public ResponseEntity<Student> addStudentInfo(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @GetMapping("/student")
    public ResponseEntity<List<Student>> getStudent(){
        return studentService.getStudent();
    }

}

