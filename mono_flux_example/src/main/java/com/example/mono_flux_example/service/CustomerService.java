package com.example.mono_flux_example.service;

import com.example.mono_flux_example.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class CustomerService {

    @Autowired
    private ReactiveRedisTemplate<String, Customer> reactiveRedisTemplate;

    public CompletableFuture<Customer> insertCustomer(Customer customer) {
        return CompletableFuture.supplyAsync(() -> {
            Mono<Customer> insertMono = reactiveRedisTemplate.opsForValue().set(customer.getId(), customer)
                    .flatMap(success -> {
                        if (success) {
                            return Mono.just(customer);
                        } else {
                            return Mono.error(new RuntimeException("Failed to insert customer"));
                        }
                    });
            return insertMono.block();
        });
    }


    public CompletableFuture<Customer> findById(String id) {
        return CompletableFuture.supplyAsync(() -> {
            Mono<Customer> findMono = reactiveRedisTemplate.opsForValue().get(id);
            return findMono.block(); // Block to get the result synchronously
        });
    }

    public CompletableFuture<Flux<Customer>> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            Flux<String> keysFlux = reactiveRedisTemplate.keys("*");
            return keysFlux.flatMap(reactiveRedisTemplate.opsForValue()::get);
        });
    }

    public CompletableFuture<Void> deleteByIdAsync(String id) {
        return CompletableFuture.supplyAsync(() -> {
            Mono<Boolean> deleteMono = reactiveRedisTemplate.opsForValue().delete(id);
            deleteMono.block();
            return null;
        });
    }
}
