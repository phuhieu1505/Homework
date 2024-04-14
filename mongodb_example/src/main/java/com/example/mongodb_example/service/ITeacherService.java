package com.example.mongodb_example.service;

import com.example.mongodb_example.entity.Student;
import com.example.mongodb_example.entity.Teacher;

import java.util.concurrent.CompletableFuture;


public interface ITeacherService {
    CompletableFuture<String> insertTeacher(Teacher teacher);
    CompletableFuture<Teacher> getTeacherById(String teacherID);
    CompletableFuture<Teacher> updateTeacherById(String teacherID, Teacher teacher);
    CompletableFuture<Void> deleteTeacherById(String teacherID);
}
