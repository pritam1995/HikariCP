package com.fresco.hikaricp.service;

import com.fresco.hikaricp.model.Customer;
import com.fresco.hikaricp.repo.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(String name, String email) {
        customerRepository.addCustomer(name, email);
    }

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }
}
