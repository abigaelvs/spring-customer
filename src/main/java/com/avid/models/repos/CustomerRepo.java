package com.avid.models.repos;

import java.util.ArrayList;

import com.avid.models.entities.Customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, String> {

  @Query(value = "SELECT * FROM ms_customer", nativeQuery = true)
  ArrayList<Customer> selectAll();

  @Query(value = "SELECT * FROM ms_customer WHERE cust_id= :id", nativeQuery = true)
  Customer selectById(String id);
}
