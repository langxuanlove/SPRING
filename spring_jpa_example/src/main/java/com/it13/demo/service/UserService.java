package com.it13.demo.service;

import java.util.List;

import com.it13.demo.domain.User;

public interface UserService {
	
	User save(User user);
	
	List<User> findAll();
	
	int updateNameById(Long id, String name);
	
	int del(Long id);
}
