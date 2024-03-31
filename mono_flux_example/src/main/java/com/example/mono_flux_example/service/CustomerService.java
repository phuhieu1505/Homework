package com.example.mono_flux_example.service;

import com.example.mono_flux_example.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private ReactiveRedisTemplate<String,Customer> reactiveRedisTemplate;

    public Mono<Customer> insertCustomer(Customer customer){
        return reactiveRedisTemplate.opsForValue()
                .set(customer.getId(), customer)
                .map(__->customer);
    }

    public Mono<Customer>findById(String id){
        return reactiveRedisTemplate.opsForValue().get(id);
    }

    public Flux<Customer>findAll(){
        return reactiveRedisTemplate.keys("*")
                .flatMap(reactiveRedisTemplate.opsForValue()::get);
    }

    public Mono<Void> deleteById(String id){
        return reactiveRedisTemplate.opsForValue().delete(id).then();
    }
}
