package com.it13.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.it13.demo.domain.User;
import com.it13.demo.service.UserService;


/**
 *  url访问地址是：http://localhost:8081/spring4-mvc-demo/user/findAll 
 *
 */

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/findAll")
	 public ModelAndView hello(String name) {
		return new ModelAndView("users").addObject("users", userService.findAll());
	 }

	@RequestMapping("/user/save")
	 public ModelAndView save(String name,int status) {
		User u = new User();
		u.setId((long)status);
		u.setName(name);
		u.setIsDeleted(String.valueOf(status));
		System.out.println(u.toString());
		return new ModelAndView("users").addObject("users", userService.save(u));
	 }

	@RequestMapping("/user/updateNameById")
	 public ModelAndView updateNameById(Long id, String name) {
		return new ModelAndView("users").addObject("users", userService.updateNameById(id, name));
	 }

	@RequestMapping("/user/del")
	 public ModelAndView del(Long id) {
		return new ModelAndView("users").addObject("users", userService.del(id));
	 }
	
}
