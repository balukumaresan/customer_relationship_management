package com.balu.gl.cutomer.relationship.management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.balu.gl.cutomer.relationship.management.entity.Customer;
import com.balu.gl.cutomer.relationship.management.service.CustomerService;

/**
 * Provide Endpoints for the ui to consum related to Customer Relationship
 * Management.
 * 
 * @author baluk
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerRelationshipController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") Integer customerId, Model model) {

		Optional<Customer> customer = customerService.findById(customerId);
		if (customer.isPresent()) {
			model.addAttribute("customer", customer.get());
		} else {
			System.out.println("No customer record exists");
		}
		return "customer-form";
	}

	@RequestMapping("/listCustomers")
	public String listAllCustomers(Model model) {

		List<Customer> customers = customerService.getAllCustomers();
		model.addAttribute("customers", customers);
		return "list-customers";
	}

	@PostMapping("/saveCustomer")
	public String addCustomer(@RequestParam(required = false, value = "customerId") Integer customerId,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email) {

		customerService.addOrUpdateCustomer(customerId, firstName, lastName, email);
		return "redirect:/customer/listCustomers";
	}

	@PutMapping("/updateCustomer")
	public Customer updateCustomer(@RequestParam("customerId") int customerId,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email) {
		return customerService.addOrUpdateCustomer(customerId, firstName, lastName, email);

	}

	@RequestMapping("/deleteCustomer")
	public String deleteCustomer(int customerId) {
		customerService.deleteCustomer(customerId);
		return "redirect:/customer/listCustomers";
	}

	@RequestMapping("/search")
	public String search(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			Model model) {

		if (firstName.trim().isEmpty() && lastName.trim().isEmpty()) {
			return "redirect:/customer/list";
		} else {
			List<Customer> customers = customerService.searchBy(firstName, lastName);

			model.addAttribute("customers", customers);

			return "list-Books";
		}

	}
}
