package com.gnet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gnet.model.User;
import com.gnet.service.IService;
import com.google.gson.Gson;

@Service(value = "service")
public class ServiceImpl implements IService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User getUser() throws Exception {
		User user = new User();
		user.setId(12);
		user.setName("成功与失败");
		Gson gson = new Gson();
		Object object = gson.toJson(jdbcTemplate
				.queryForList("select * from service_online;"));
		System.out.println(object);
		return user;
	}
}
