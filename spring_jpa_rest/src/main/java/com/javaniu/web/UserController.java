package com.javaniu.web;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaniu.domain.User;
import com.javaniu.repository.UserRepository;

@Controller
@RequestMapping({ "/user" })
public class UserController {
	@Autowired
	UserRepository repository;

	@RequestMapping(value = { "" })
	public @ResponseBody
	JSONObject index() {
		JSONObject json = new JSONObject();
		List<User> users = repository.findAll();
		json.put("users", users);
		return json;
	}

}
