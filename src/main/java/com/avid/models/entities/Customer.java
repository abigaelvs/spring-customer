package com.avid.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ms_customer")
public class Customer implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "cust_id", length = 50)
  private String custId;

  @Column(name = "cust_name", length = 200, nullable = false)
  private String custName;

  @Column(length = 100, nullable = false)
  private String address;

  @Column(name = "phone_no", length = 50, nullable = false)
  private String phoneNo;

  public Customer(String custId, String custName, String address, String phoneNo) {
    this.custId = custId;
    this.custName = custName;
    this.address = address;
    this.phoneNo = phoneNo;
  }

  public Customer() {
  }

  public String getCustId() {
    return custId;
  }

  public void setCustId(String custId) {
    this.custId = custId;
  }

  public String getCustName() {
    return custName;
  }

  public void setCustName(String custName) {
    this.custName = custName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }
}
