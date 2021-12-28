package com.balu.gl.debate.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.balu.gl.debate.student.management.dao.UserRepository;
import com.balu.gl.debate.student.management.entity.User;
import com.balu.gl.debate.student.management.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public MyUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = userRepository.getUserByUserName(userName);	if (null == user) 
		{
			throw new UsernameNotFoundException("Sorry User Not found");
		}
		else {
			return new MyUserDetails(user);
		}
	}
}
