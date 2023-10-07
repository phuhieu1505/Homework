package com.example.demo.repository;

import com.example.demo.model.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    @Query("{'stu_id': ?0}")
    Student findStudentByStuId(String stu_id);

}
