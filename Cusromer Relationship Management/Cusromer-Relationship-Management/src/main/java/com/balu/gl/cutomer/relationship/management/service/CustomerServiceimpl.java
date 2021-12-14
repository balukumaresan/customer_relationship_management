package com.balu.gl.cutomer.relationship.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.balu.gl.cutomer.relationship.management.dao.CustomerRepo;
import com.balu.gl.cutomer.relationship.management.entity.Customer;

@Service
public class CustomerServiceimpl implements CustomerService {

	@Autowired
	private CustomerRepo custRepo;

	public List<Customer> getAllCustomers() {
		return custRepo.findAll();
	}

	public Optional<Customer> findById(Integer customerId) {
		return custRepo.findById(customerId);
	}

	public Customer addOrUpdateCustomer(Integer customerId, String firstName, String lastName, String email) {
		
		Customer cust = null;
		
		if (null != customerId) {
			Optional<Customer> custOptional = custRepo.findById(customerId);
			cust = custOptional.get();
		} else {
			cust = new Customer();
		}
		cust.setFirstName(firstName);
		cust.setLastName(lastName);
		cust.setEmail(email);

		return custRepo.save(cust);
	}
	
	@Override
	public List<Customer> searchBy(String firstName, String lastName) {
		List <Customer> customers = custRepo.findByFirstNameAndLastName(firstName, lastName);
		return customers;
	}
	
	public void deleteCustomer(Integer custId) {
		Optional<Customer> cust = custRepo.findById(custId);
		custRepo.delete(cust.get());
	}
}
