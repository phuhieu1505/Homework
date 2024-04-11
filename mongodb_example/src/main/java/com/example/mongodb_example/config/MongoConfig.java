package com.example.mongodb_example.config;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MongoConfig  {


    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean(name ={"student_template"})
    public ReactiveMongoTemplate studentReactiveMongoTemplate(){
        return new ReactiveMongoTemplate(mongoClient(),"students");
    }
    @Bean(name ={"teacher_template"})
    public ReactiveMongoTemplate teacherReactiveMongoTemplate(){
        return new ReactiveMongoTemplate(mongoClient(),"teachers");
    }

}
