package com.gnet.repository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.gnet.domain.User;


public interface UserRepository extends CrudRepository<User, String>,
		JpaSpecificationExecutor<User> {

	@Modifying
	@Query("update User a set a.name = ?2 where a.id = ?1")
	int updateNameById(String id, String name);
	
	@Modifying
	@Query("update User a set a.isDeleted = 1 where a.id = ?1")
	int del(String id);
	
	
}
