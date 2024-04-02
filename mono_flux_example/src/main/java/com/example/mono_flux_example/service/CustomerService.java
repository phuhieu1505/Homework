package com.example.mono_flux_example.service;

import com.example.mono_flux_example.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
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

    public CompletableFuture<List<Customer>> insertCustomers(List<Customer> customers) {
        CompletableFuture<List<Customer>> completableFuture = new CompletableFuture<>();

        Flux<Customer> customerFlux = Flux.fromIterable(customers);
        customerFlux.flatMap(customer ->
                        reactiveRedisTemplate.opsForValue().set(customer.getId(), customer).thenReturn(customer))
                .collectList()
                .subscribe(
                        result -> completableFuture.complete(result),
                        error -> completableFuture.completeExceptionally(error)
                );

        return completableFuture;
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

    public CompletableFuture<Long> incrementCounter(String key) {
        return CompletableFuture.supplyAsync(() -> {
            Mono<Customer> customerMono = reactiveRedisTemplate.opsForValue().get(key);
            return customerMono.flatMap(customer -> {
                customer.setCounterValue(customer.getCounterValue() + 1); 
                return reactiveRedisTemplate.opsForValue().set(key, customer).map(__ -> customer.getCounterValue());
            }).block();
        });
    }

    public CompletableFuture<Long> getCurrentValue(String key) {
        return CompletableFuture.supplyAsync(() -> {
            Mono<Customer> customerMono = reactiveRedisTemplate.opsForValue().get(key);
            return customerMono.map(Customer::getCounterValue).block();
        });
    }
}
