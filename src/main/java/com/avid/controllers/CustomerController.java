package com.avid.controllers;

import com.avid.models.entities.Customer;
import com.avid.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping
  public Customer create(@RequestBody Customer customer) {
    return customerService.save(customer);
  }

  @GetMapping
  public Iterable<Customer> findAll() {
    return customerService.findAll();
  }

  @GetMapping("/{id}")
  public Customer findOne(@PathVariable("id") String id) {
    return customerService.selectById(id);
  }
}
