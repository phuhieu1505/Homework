package com.example.mongodb_example.service;

import com.example.mongodb_example.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class TeacherService implements ITeacherService{
    @Autowired
    @Qualifier("teacher_template")
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public CompletableFuture<String> insertTeacher(Teacher teacher){
        return Mono.just(teacher)
                .flatMap(reactiveMongoTemplate::insert)
                .map(t -> t.getTeacherID())
                .doOnNext(t -> {
                    Cache cache = cacheManager.getCache("teacher");
                    if(cache != null)
                         cache.put(t,teacher);
                })
                .toFuture();
    }

    @Override
    public CompletableFuture<Teacher> getTeacherById(String teacherID){
        Mono<Teacher> teacherMono = reactiveMongoTemplate.findById(teacherID, Teacher.class);
        return teacherMono.toFuture();
    }

    @Override
    public CompletableFuture<Teacher> updateTeacherById(String teacherID, Teacher teacher){
        Mono<Teacher> teacherMono = reactiveMongoTemplate.findById(teacherID, Teacher.class);
        return teacherMono.flatMap(existTeacher ->
        {
            if(existTeacher != null){
                existTeacher.setTeacherID(teacher.getTeacherID());
                existTeacher.setTeacherName(teacher.getTeacherName());
                return reactiveMongoTemplate.save(existTeacher);
            }else {
                return Mono.empty();
            }
        }).toFuture();
    }

    @Override
    public CompletableFuture<Void> deleteTeacherById(String teacherID){
        Mono<Teacher> teacherMono = reactiveMongoTemplate.findById(teacherID, Teacher.class);
        return teacherMono.flatMap(existTeacher ->{
            if(existTeacher != null){
                Mono<Void> deleteResult =reactiveMongoTemplate.remove(Query.query(Criteria.where("teacherID").is(teacherID)))
                        .then();
                cacheManager.getCache("teacher").evict(teacherID);
                return deleteResult;
            }else {
                return Mono.empty();
            }
        }).toFuture();
    }
}
