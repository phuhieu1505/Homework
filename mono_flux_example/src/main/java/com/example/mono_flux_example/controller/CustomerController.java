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
   public Mono<Customer> insertCustomer(@RequestBody Customer customer){
     return customerService.insertCustomer(customer);
  }

  @GetMapping
   public Flux<Customer> getAllCustomer(){
     return customerService.findAll();
  }

  @GetMapping("/{id}")
   public Mono<Customer> getCustomerById(@PathVariable String   id){
     return customerService.findById(id);
  }

  @DeleteMapping("/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id){
      return customerService.deleteById(id);
  }
}
