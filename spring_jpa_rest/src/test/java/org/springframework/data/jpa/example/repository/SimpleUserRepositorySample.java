package org.springframework.data.jpa.example.repository;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javaniu.domain.User;
import com.javaniu.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SimpleUserRepositorySample {

	@Autowired
	UserRepository repository;
	User user;

	@Before
	public void setUp() {
		user = new User();
		user.setUserName("foobar");
		user.setPassword("" + System.currentTimeMillis());
	}

	/**
	 * Tests inserting a user and asserts it can be loaded again.
	 */
	@Test
	public void testInsert() {

		user = repository.save(user);

		System.out.println(user);

	}

	@Test
	public void foo() throws Exception {
		List<User> users = repository.findByUserName("foobar");
		for (User user : users) {
			System.out.println(user);
		}
	}
}
