package com.balu.gl.debate.student.management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.balu.gl.debate.student.management.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.userName = ?1")
	public User getUserByUserName(String userNme);

}
