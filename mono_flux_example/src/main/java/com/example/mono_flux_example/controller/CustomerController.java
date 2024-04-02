package com.example.mono_flux_example.controller;

import com.example.mono_flux_example.model.Customer;
import com.example.mono_flux_example.model.ResponseData;
import com.example.mono_flux_example.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping
	public Mono<ResponseData> getAllCustomerAsync() {
		var stage = customerService.findAllCustomers()
				.thenApply(entities -> entities.stream().map(e -> objectMapper.convertValue(e, Map.class)).collect(Collectors.toList()))
				.exceptionally(ex -> {
					System.out.println(ex);
					return Collections.emptyList();
				});

		return Mono.fromCompletionStage(stage)
				.map(data -> ResponseData.builder().status(!data.isEmpty()).data(data).build());
	}


//	@PostMapping("/insert")
//	public CompletableFuture<Customer> insertCustomerAsync(@RequestBody Customer customer) {
//		return customerService.insertCustomer(customer);
//	}
//
//	@PostMapping("/insertCustomers")
//	public CompletableFuture<List<Customer>> insertCustomers(@RequestBody List<Customer> customers) {
//		return customerService.insertCustomers(customers);
//	}
//
//	@GetMapping
//	public CompletableFuture<Flux<Customer>> getAllCustomerAsync() {
//		return customerService.findAll();
//	}
//
//	@GetMapping("/{id}")
//	public CompletableFuture<Customer> getCustomerByIdAsync(@PathVariable String id) {
//		return customerService.findById(id);
//	}
//
//	@DeleteMapping("/{id}")
//	public CompletableFuture<Void> deleteCustomerAsync(@PathVariable String id) {
//		return customerService.deleteByIdAsync(id);
//	}
//
//	@PostMapping("/increment/{customerId}")
//	public CompletableFuture<Long> incrementCounter(@PathVariable String customerId) {
//		return customerService.incrementCounter(customerId);
//	}
//
//	@GetMapping("/currentValue/{customerId}")
//	public CompletableFuture<Long> getCurrentValue(@PathVariable String customerId) {
//		return customerService.getCurrentValue(customerId);
//	}

}
