package com.avid.services;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import com.avid.models.entities.Customer;
import com.avid.models.repos.CustomerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService {

  @Autowired
  private CustomerRepo customerRepo;

  public Customer save(Customer customer) {
    Long countCustomer = customerRepo.count();
    System.out.println(countCustomer);
    String id = String.format("%05d", 1);
    if (countCustomer > 0) {
      id = String.format("%05d", countCustomer + 1);
    }
    customer.setCustId(id);
    return customerRepo.save(customer);
  }

  public Customer findOne(String id) {
    Optional<Customer> customer = customerRepo.findById(id);
    if (!customer.isPresent()) {
      return null;
    }

    return customer.get();
  }

  public Iterable<Customer> findAll() {
    return customerRepo.findAll();
  }

  public ArrayList<Customer> selectAll() {
    return customerRepo.selectAll();
  }

  public Customer selectById(String id) {
    return customerRepo.selectById(id);
  }
}
