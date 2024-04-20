package com.example.mongodb_example.service;

import com.example.mongodb_example.entity.Student;
import com.example.mongodb_example.entity.Teacher;

import java.util.concurrent.CompletableFuture;


public interface IStudentService {
    CompletableFuture<String> insertStudent(Student student);
    CompletableFuture<Student> getStudentByID(String stuID);
    CompletableFuture<Student> updateStudentById(String stuID, Student student);

    CompletableFuture<Void> deleteStudentById(String id);
}
