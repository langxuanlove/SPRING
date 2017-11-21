package com.javaniu.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaniu.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUserName(String username);
}
