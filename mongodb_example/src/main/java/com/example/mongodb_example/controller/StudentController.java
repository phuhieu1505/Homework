package com.example.mongodb_example.controller;

import com.example.mongodb_example.entity.Student;
import com.example.mongodb_example.repository.StudentRepository;
import com.example.mongodb_example.service.StudentsService;
import com.example.mongodb_example.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/students")
public class StudentController {
   @Autowired
    private IStudentService service;
//    @GetMapping("/all")
//    public ResponseEntity<List<Student>> getAllStudents(){
//        return service.getAllStudents();
//    }
//
//    @PostMapping("/insert")
//    public ResponseEntity<Student> insertStudent(@RequestBody Student student){
//        return service.insertStudent(student);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student student){
//        return service.updateStudent(id,student);
//    }

    @PostMapping("/insert")
    public CompletableFuture<String> insertStudent(@RequestBody Student student){
        return service.insertStudent(student);
    }

}
