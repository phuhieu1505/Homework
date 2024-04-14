package com.example.mongodb_example.controller;

import com.example.mongodb_example.entity.ResponseData;
import com.example.mongodb_example.entity.Student;
import com.example.mongodb_example.entity.Teacher;
import com.example.mongodb_example.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @PostMapping("/insert")
    public Mono<ResponseData> insertTeacher(@RequestBody Teacher teacher){
        var future = teacherService.insertTeacher(teacher)
                .thenApply(teacherID -> teacherID)
                .exceptionally(ex -> ex.getMessage());
        return Mono.fromFuture(future)
                .map(data -> ResponseData.builder().status(!data.isEmpty()).data(data).build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseData> getTeacherById(@PathVariable String id) {
        var future = teacherService.getTeacherById(id)
                .thenApply(teacher -> {
                    if (teacher != null) {
                        return ResponseData.builder().status(true).data(teacher).build();
                    } else {
                        return ResponseData.builder().status(false).data("Teacher not found").build();
                    }
                })
                .exceptionally(ex -> ResponseData.builder().status(false).data(ex.getMessage()).build());

        return Mono.fromFuture(future);
    }

    @PutMapping("/{id}")
    public Mono<ResponseData> updateTeacher(@PathVariable String id, @RequestBody Teacher teacher){
        var future = teacherService.updateTeacherById(id,teacher)
                .thenApply(existTeacher ->{
                    if(existTeacher != null) {
                        return ResponseData.builder().status(true).data(existTeacher).build();
                    }else {
                        return ResponseData.builder().status(false).data("Not found").build();
                    }
                })
                .exceptionally(ex -> ResponseData.builder().status(false).data(ex.getMessage()).build());

        return Mono.fromFuture(future);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseData> deleteTeacher(@PathVariable String id){
        var future = teacherService.getTeacherById(id)
                .thenCompose(rs -> {
                    if(rs != null){
                        return teacherService.deleteTeacherById(id)
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
