package com.balu.gl.cutomer.relationship.management.service;

import java.util.List;
import java.util.Optional;

import com.balu.gl.cutomer.relationship.management.entity.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers();
	
	public Optional<Customer> findById(Integer customerId) ;
	
	public Customer addOrUpdateCustomer(Integer customerId, String firstName, String lastName,
			String email);
	
	public void deleteCustomer(Integer custId) ;

	public List<Customer> searchBy(String firstName, String lastName);
}
