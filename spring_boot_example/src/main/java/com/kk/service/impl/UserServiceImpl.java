package com.gnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gnet.domain.User;
import com.gnet.repository.UserRepository;
import com.gnet.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Transactional
	@Override
	public int updateNameById(String id, String name) {
		return userRepository.updateNameById(id, name);
	}

	@Transactional
	@Override
	public int del(String id) {
		return userRepository.del(id);
	}

}
