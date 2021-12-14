package com.balu.gl.cutomer.relationship.management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balu.gl.cutomer.relationship.management.entity.Customer;

@Repository
public interface CustomerRepo  extends JpaRepository<Customer, Integer>{

	List <Customer> findByFirstNameAndLastName(String fistName,String lastName);
}
