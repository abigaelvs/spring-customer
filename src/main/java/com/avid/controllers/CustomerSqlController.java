package com.avid.controllers;

import com.avid.models.entities.Customer;
import com.avid.services.CustomerSqlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customersql")
public class CustomerSqlController {

  @Autowired
  private CustomerSqlService customerService;

  @GetMapping
  public Iterable<Customer> findAll() {
    return customerService.findAll();
  }
  
  @GetMapping("/{id}")
  public Customer findById(@PathVariable("id") String id) {
    return customerService.findById(id);
  }

  @PostMapping
  public Customer save(@RequestBody Customer customer) {
    return customerService.save(customer);
  }
}
