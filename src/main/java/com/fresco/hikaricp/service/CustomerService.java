package com.fresco.hikaricp.service;

import com.fresco.hikaricp.model.Customer;
import com.fresco.hikaricp.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void addCustomer(String name, String email) {
        customerRepository.addCustomer(name, email);
    }

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }
}
