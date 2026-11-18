package com.billing.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="billing_status")
public class BillingStatus {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   int id;
   
   
   @Column(name="status_name")
   String statusName;
   
   
   @Column(name="status_value")
   int statusValue;
   
   
   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public int getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(int statusValue) {
		this.statusValue = statusValue;
	}
	   
   
 

}
