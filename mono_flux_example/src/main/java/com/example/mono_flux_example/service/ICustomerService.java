package com.example.mono_flux_example.service;

import com.example.mono_flux_example.model.Customer;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface ICustomerService {

	CompletionStage<List<Customer>> findAllCustomers();

}
