package com.example.mono_flux_example.controller;

import com.example.mono_flux_example.model.Customer;
import com.example.mono_flux_example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/customer")
public class CustomerController {

   @Autowired
   private CustomerService customerService;

    @PostMapping("/insert")
    public CompletableFuture<Customer> insertCustomerAsync(@RequestBody Customer customer) {
        return customerService.insertCustomer(customer);
    }

    @PostMapping("/insertCustomers")
    public CompletableFuture<List<Customer>> insertCustomers(@RequestBody List<Customer> customers) {
        return customerService.insertCustomers(customers);
    }


    @GetMapping
    public CompletableFuture<Flux<Customer>> getAllCustomerAsync() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CompletableFuture<Customer> getCustomerByIdAsync(@PathVariable String id) {
        return customerService.findById(id);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<Void> deleteCustomerAsync(@PathVariable String id) {
        return customerService.deleteByIdAsync(id);
    }

    @PostMapping("/increment/{customerId}")
    public CompletableFuture<Long> incrementCounter(@PathVariable String customerId) {
        return customerService.incrementCounter(customerId);
    }

    @GetMapping("/currentValue/{customerId}")
    public CompletableFuture<Long> getCurrentValue(@PathVariable String customerId) {
        return customerService.getCurrentValue(customerId);
    }

}
