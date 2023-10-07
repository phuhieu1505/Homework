package com.example.demo.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "student")
public class Student {

    @Id
    private String id;
    private String stu_name;
    private int age;
    private String class_id;

}
