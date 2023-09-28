package com.example.demo.Service;

import com.example.demo.Repository.StudentRepository;
import com.example.demo.Student;
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

    public ResponseEntity<Student> addStudent(Student student){
        return new ResponseEntity<>(studentRepository.save(student),HttpStatus.CREATED);
    }

    public ResponseEntity<List<Student>>getStudent(){
        try{
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
    }


}
