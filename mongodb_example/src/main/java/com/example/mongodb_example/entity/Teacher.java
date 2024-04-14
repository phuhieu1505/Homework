package com.example.mongodb_example.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="teachers")
public class Teacher {
    @Id
    private String teacherID;
    private String teacherName;

}
