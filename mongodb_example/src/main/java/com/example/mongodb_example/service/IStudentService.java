package com.example.mongodb_example.service;

import com.example.mongodb_example.entity.Student;

import java.util.concurrent.CompletableFuture;

public interface IStudentService {
    CompletableFuture<String> insertStudent(Student student);
}
