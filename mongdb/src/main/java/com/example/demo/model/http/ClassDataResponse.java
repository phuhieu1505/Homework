package com.example.demo.model.http;

import com.example.demo.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDataResponse {
    private String class_id;
    private String class_name;
    private List<Student> students;
}
