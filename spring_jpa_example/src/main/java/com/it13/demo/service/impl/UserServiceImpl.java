package com.it13.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it13.demo.domain.User;
import com.it13.demo.repository.UserRepository;
import com.it13.demo.service.UserService;

@Service
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
	public int updateNameById(Long id, String name) {
		return userRepository.updateNameById(id, name);
	}

	@Transactional
	@Override
	public int del(Long id) {
		return userRepository.del(id);
	}

}
