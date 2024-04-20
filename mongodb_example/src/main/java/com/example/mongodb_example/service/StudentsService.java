package com.example.mongodb_example.service;

import com.example.mongodb_example.entity.Student;
import com.example.mongodb_example.entity.Teacher;
import com.example.mongodb_example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class StudentsService implements IStudentService {
    @Autowired
    @Qualifier("student_template")
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ReactiveRedisTemplate<String,Student> redisTemplate;

    @Autowired
    private ITeacherService teacherService;

//    @Override
//    public CompletableFuture<String> insertStudent(Student student){
//        return Mono.just(student)
//                .flatMap(reactiveMongoTemplate::insert)
//                .flatMap(stu ->{
//                    if(stu.getTeacherID() != null){
//                        Teacher teacher = new Teacher();
//                        return Mono.fromFuture(teacherService.getTeacherById(teacher.getTeacherID())
//                                .thenApply(t ->t));
//                    }else {
//                        return Mono.just(stu);
//                    }
//                })
//                .map(s -> student.getStuID())
//                .doOnNext(stuID ->{
//                    Cache cache = cacheManager.getCache("studentCache");
//                    if(cache !=null){
//                        cache.put(stuID,student);
//                    }
//                })
//                .toFuture();
//    }
    @Override
    public CompletableFuture<String> insertStudent(Student student){
        return Mono.just(student)
                .flatMap(reactiveMongoTemplate::insert)
                .map(s -> s.getStuID())
                .flatMap(stuID -> {
                    String key = "student:" + stuID;
                    return redisTemplate.opsForValue().set(key,student)
                            .flatMap(rs ->{
                                if(rs){
                                    Duration ttl = Duration.ofMinutes(5);
                                    return redisTemplate.expire(key,ttl);
                                }else {
                                    return Mono.empty();
                                }
                            });
                })
                .thenReturn(student.getStuID())
                .toFuture();
    }

    @Override
    public CompletableFuture<Student>getStudentByID(String stuID){
        Mono<Student> studentMono = reactiveMongoTemplate.findById(stuID, Student.class);
        return studentMono.toFuture();
    }

    @Override
    public CompletableFuture<Student> updateStudentById(String stuID, Student student) {
        Mono<Student> studentMono = reactiveMongoTemplate.findById(stuID, Student.class);
        return studentMono.flatMap(existingStudent -> {
            if (!existingStudent.getStuID().isEmpty()) {

                existingStudent.setStuName(student.getStuName());
                existingStudent.setStuClass(student.getStuClass());
                existingStudent.setBatch(student.getBatch());

                return reactiveMongoTemplate.save(existingStudent)
                        .flatMap(updatedStudent -> {
                            String key = "student:" + updatedStudent.getStuID();
                            return redisTemplate.opsForValue().set(key, updatedStudent)
                                    .flatMap(rs ->{
                                        if(rs){
                                            Duration ttl = Duration.ofMinutes(5);
                                            return redisTemplate.expire(key,ttl);
                                        }else {
                                            return Mono.empty();
                                        }
                                    })
                                    .then(Mono.just(updatedStudent));
                        });
            } else {
                return Mono.empty();
            }
        }).toFuture();
    }


    @Override
    public CompletableFuture<Void> deleteStudentById(String stuID){
        Mono<Student> studentMono = reactiveMongoTemplate.findById(stuID, Student.class);
        return studentMono.flatMap(existingStudent -> {
           if(existingStudent != null){
               Mono<Void> deleteResult = reactiveMongoTemplate.remove(Query.query(Criteria.where("stuID").is(stuID)), Student.class)
                       .then();
               cacheManager.getCache("studentCache").evict(stuID);
               return deleteResult;
           }else {
               return Mono.empty();
           }
        }).toFuture();
    }
}
