package com.example.demo.controller;

import com.example.demo.model.http.ClassDataResponse;
import com.example.demo.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/class")
public class ClassController {
    @Autowired
    ClassesService classesService;
    @GetMapping("/get-all")
    public ResponseEntity<List<ClassDataResponse>> getAllClasses(){
        return classesService.getAllClasses();
    }
    @GetMapping("/get/")
    public ResponseEntity<ClassDataResponse> getClassById(@RequestParam("id") String class_id){
        return classesService.getClassById(class_id);
    }
}
