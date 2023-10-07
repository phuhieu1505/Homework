package com.example.demo.repository;

import com.example.demo.model.entity.Classes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends MongoRepository<Classes,String> {
}
