package com.example.demo.service;

import com.example.demo.model.entity.Classes;
import com.example.demo.model.entity.Student;
import com.example.demo.model.http.ClassDataResponse;
import com.example.demo.repository.ClassesRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ClassesService {

    @Autowired
    ClassesRepository classesRepository;
    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity<List<ClassDataResponse>> getAllClasses(){
        List<Classes> classList = classesRepository.findAll();
        if(!classList.isEmpty()){
            List<ClassDataResponse> classDataResponseList = new ArrayList<>();

            for(Classes classes: classList){
                List<Student> studentList = studentRepository.findStudentByClassId(classes.getClass_id());
                ClassDataResponse classDataResponse = new ClassDataResponse();
                classDataResponse.setClass_id(classes.getClass_id());
                classDataResponse.setClass_name(classes.getClass_name());
                classDataResponse.setStudents(studentList);
                classDataResponseList.add(classDataResponse);
            }
            return new ResponseEntity<>(classDataResponseList, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<ClassDataResponse> getClassById(String class_id){
        Classes classes = classesRepository.getClassesByClassId(class_id);
        ClassDataResponse classDataResponse = new ClassDataResponse();
        if(classes != null){
            List<Student> studentList = studentRepository.findStudentByClassId(classes.getClass_id());
            classDataResponse.setClass_id(classes.getClass_id());
            classDataResponse.setClass_name(classes.getClass_name());
            classDataResponse.setStudents(studentList);
            return new ResponseEntity<>(classDataResponse,HttpStatus.FOUND);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
