package com.example.demo.Repository;

import com.example.demo.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class StudentRepository {
    private List<Student> studentList = new ArrayList<Student>();

    public List<Student> getAllStudent(){
        return studentList;
    }

    public Student add(Student st){
        Student student = new Student();
        student.setId(st.getId());
        student.setName(st.getName());
        student.setAge(st.getAge());
        studentList.add(student);
        System.out.println(studentList);
        return student;
    }

}
