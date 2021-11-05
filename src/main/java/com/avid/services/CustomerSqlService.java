package com.avid.services;

import javax.transaction.Transactional;

import com.avid.models.entities.Customer;
import com.avid.models.repos.CustomerSqlRepo;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerSqlService {

  private CustomerSqlRepo customerSqlRepo = new CustomerSqlRepo();

  public Customer findById(String id) {
    return customerSqlRepo.select(id);
  }

  public Iterable<Customer> findAll() {
    return customerSqlRepo.getAll();
  }

  public Customer save(Customer customer) {
    return customerSqlRepo.insert(customer);
  }
}
