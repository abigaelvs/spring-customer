package com.avid.models.repos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.avid.models.entities.Customer;

public class CustomerSqlRepo {
  private Connection dbConnect;
  private String dbDriver = "com.mysql.jdbc.Driver";
  private String dbUrl = "jdbc:mysql://localhost/my_db";
  private String dbUsername = "root";
  private String dbPass = "";

  // Connect to database
  private void connect() {
		try {
			Class.forName(dbDriver);
			dbConnect = DriverManager.getConnection(dbUrl, dbUsername, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
  // Disconnect from database
	private void disconnect() {
		try {
			if (dbConnect != null) {
				dbConnect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

  // Get customer based on ID
  public Customer select(String id) {

    Customer customer = new Customer();
    try {
      connect();

      String sql = "SELECT * FROM ms_customer WHERE cust_id = ?";

      PreparedStatement ps = dbConnect.prepareStatement(sql);
      ps.setString(1, id);

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        customer.setCustId(rs.getString("cust_id"));
        customer.setCustName(rs.getString("cust_name"));
        customer.setAddress(rs.getString("address"));
        customer.setPhoneNo(rs.getString("phone_no"));
      }

      disconnect();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return customer;
  }

  // Get all customer
  public Iterable<Customer> getAll() {

    ArrayList<Customer> list = new ArrayList<Customer>();

    try {
      connect();

      String sql = "SELECT * FROM ms_customer";
      PreparedStatement ps = dbConnect.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Customer customer = new Customer();
        customer.setCustId(rs.getString("cust_id"));
        customer.setCustName(rs.getString("cust_name"));
        customer.setAddress(rs.getString("address"));
        customer.setPhoneNo(rs.getString("phone_no"));

        list.add(customer);
      }

      disconnect();
      
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  // Set customer
  public Customer insert(Customer customer) {
    Customer newCustomer = null;
    try {
      connect();

      // count all row in the database to increment the id
      Statement stmt = dbConnect.createStatement();
      String countSql = "SELECT count(*) FROM ms_customer";
      ResultSet rs = stmt.executeQuery(countSql);
      rs.next();
      Integer countCustomer = rs.getInt(1);

      String sql = "INSERT INTO ms_customer (cust_id, cust_name, address, phone_no) VALUES (?, ?, ?, ?)";

      PreparedStatement ps = dbConnect.prepareStatement(sql);

      String id = String.format("%05d", 1);

      if (countCustomer > 0) {
        id = String.format("%05d", countCustomer + 1);
      }
      customer.setCustId(id);

      ps.setString(1, customer.getCustId());
      ps.setString(2, customer.getCustName());
      ps.setString(3, customer.getAddress());
      ps.setString(4, customer.getPhoneNo());

      ps.executeUpdate();

      newCustomer = customer;

    } catch (Exception e) {
      e.printStackTrace();
    }

    return newCustomer;
  }
}