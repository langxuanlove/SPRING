package com.gnet.service;

import java.util.List;

import com.gnet.domain.User;


public interface UserService {
	
	User save(User user);
	
	List<User> findAll();
	
	int updateNameById(String id, String name);
	
	int del(String id);
}
