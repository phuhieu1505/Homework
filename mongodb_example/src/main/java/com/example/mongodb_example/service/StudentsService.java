package com.example.mongodb_example.service;

import com.example.mongodb_example.entity.Student;
import com.example.mongodb_example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {
    @Autowired
    private StudentRepository repository;
    public ResponseEntity<List<Student>> getAllStudents(){
        try{
            List<Student> studentList = repository.findAll();
            if(!studentList.isEmpty())
                return new ResponseEntity<>(studentList, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Student> insertStudent(Student student){
        try{
            Student newStudent = repository.save(student);
            if(student.getStuName().equals("") || student.getBatch() < 2000 || student.getStuClass().equals("") )
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(newStudent,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Student> updateStudent(String id,Student student){
            try{
                Optional<Student> studentOptional = repository.findById(id);
                if(studentOptional.isPresent()){
                    Student newStudent = studentOptional.get();
                    if(!newStudent.getStuName().equals("") || newStudent.getBatch() >= 2000 || !newStudent.getStuClass().equals("") ){
                        newStudent.setStuName(student.getStuName());
                        newStudent.setStuClass(student.getStuClass());
                        newStudent.setBatch(student.getBatch());
                        return new ResponseEntity<>(repository.save(newStudent),HttpStatus.OK);
                    }
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch (Exception e){
                return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
