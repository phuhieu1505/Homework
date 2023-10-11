package com.example.demo.controller;

import com.example.demo.model.entity.Classes;
import com.example.demo.model.http.ClassDataResponse;
import com.example.demo.model.http.CreateClassRequest;
import com.example.demo.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/post")
    public ResponseEntity<Classes> addClass(@RequestBody CreateClassRequest request) throws Exception{
        return classesService.addClass(request);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ClassDataResponse> deleteClass(@RequestParam("id") String stu_id){
        return classesService.deleteClass(stu_id);
    }
}
