package com.example.mongodb_example.controller;

import com.example.mongodb_example.entity.ResponseData;
import com.example.mongodb_example.entity.Student;
import com.example.mongodb_example.repository.StudentRepository;
import com.example.mongodb_example.service.StudentsService;
import com.example.mongodb_example.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
/***************************************/
//    @PostMapping("/insert")
////    public CompletableFuture<String> insertStudent(@RequestBody Student student){
////        return service.insertStudent(student)
////                .thenApply(stuID -> stuID)
////                .exceptionally(ex -> ex.getMessage());
////    }

    @PostMapping("/insert")
    public Mono<ResponseData> insertStudent(@RequestBody Student student){
        var future = service.insertStudent(student)
                .thenApply(stuID -> stuID)
                .exceptionally(ex -> ex.getMessage());
        return Mono.fromFuture(future)
                .map(data -> ResponseData.builder().status(!data.isEmpty()).data(data).build());
    }

//    @GetMapping("/{id}")
//    public Mono<ResponseData> getStudentById (@PathVariable String id) {
//        var future = service.getStudentByID(id)
//                .thenApply(student -> {
//                    if(!student.getStuID().isEmpty()){
//                        return student;
//                    }else {
//                        throw  new RuntimeException("Student doesn't exists");
//                    }
//                })
//                .exceptionally(ex -> {
//                    System.out.println("Error"+ ex.getMessage());
//                    return null;
//                });
//        return Mono.fromFuture(future)
//                .map(data -> ResponseData.builder().status(!data.getStuID().isEmpty()).data(data).build());
//    }

    @GetMapping("/{id}")
    public Mono<ResponseData> getStudentById(@PathVariable String id) {
        var future = service.getStudentByID(id)
                .thenApply(student -> {
                    if (student != null) {
                        return ResponseData.builder().status(true).data(student).build();
                    } else {
                        return ResponseData.builder().status(false).data("Student not found").build();
                    }
                })
                .exceptionally(ex -> ResponseData.builder().status(false).data(ex.getMessage()).build());

        return Mono.fromFuture(future);
    }

     @PutMapping("/{id}")
    public Mono<ResponseData> updateStudent(@PathVariable String id, @RequestBody Student student){
        var future = service.updateStudentById(id,student)
                .thenApply(existingStudent ->{
                    if(existingStudent != null) {
                       return ResponseData.builder().status(true).data(existingStudent).build();
                    }else {
                       return ResponseData.builder().status(false).data("Not found").build();
                    }
                })
                .exceptionally(ex -> ResponseData.builder().status(false).data(ex.getMessage()).build());

        return Mono.fromFuture(future);
     }

//     @DeleteMapping("/{id}")
//     public Mono<ResponseData> deleteStudentById(@PathVariable String id){
//        var future = service.getStudentByID(id)
//                .thenApply(rs ->  {
//                    if(rs != null){
//                        return service.deleteStudentById(id)
//                                .thenApply(s -> s);
//                    }else {
//                        return "Not found";
//                    }
//                })
//                .exceptionally(ex -> ResponseData.builder().status(false).data(ex.getMessage()).build());
//        return Mono.fromFuture(future)
//                .map(data -> ResponseData.builder().status(true).data("Delete success").build());
//    }

    @DeleteMapping("/{id}")
    public Mono<ResponseData> deleteStudentById(@PathVariable String id){
        var future = service.getStudentByID(id)
                .thenCompose(rs -> {
                    if(rs != null){
                        return service.deleteStudentById(id)
                                .thenApply(deletedStudent -> ResponseData.builder().status(true).data("Delete success").build());
                    } else {
                        CompletableFuture<ResponseData> notFoundFuture = CompletableFuture.completedFuture(ResponseData.builder().status(false).data("Not found").build());
                        return notFoundFuture;
                    }
                })
                .exceptionally(ex -> ResponseData.builder().status(false).data(ex.getMessage()).build());
        return Mono.fromFuture(future);
    }


}
