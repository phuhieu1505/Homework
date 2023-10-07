package com.example.demo.model.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateStudentRequest {
	private String stu_id;
	private String stu_name;
	private Integer age;
	private String class_id;
}
