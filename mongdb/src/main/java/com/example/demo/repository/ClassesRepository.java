package com.example.demo.repository;

import com.example.demo.model.entity.Classes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends MongoRepository<Classes,String> {
    @Query("{'class_id': ?0}")
    Classes getClassesByClassId(String class_id);
}
