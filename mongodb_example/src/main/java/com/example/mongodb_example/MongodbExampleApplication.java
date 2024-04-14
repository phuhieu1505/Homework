package com.example.mongodb_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MongodbExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbExampleApplication.class, args);
	}

}
