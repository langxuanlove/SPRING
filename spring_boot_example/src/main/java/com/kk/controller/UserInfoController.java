package com.gnet.controller;


import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.gnet.domain.User;
import com.gnet.service.UserService;
import com.google.gson.Gson;

/**
 * 
 * 目前有两个数据源，一个是jdbctemplate,一个是hibernate的数据源，hibernate数据源主要用来提供jpa功能
 * 
 * persistence.xml必须放在META-INF文件下,不然会报错
 * 
 * @author Jikey
 * @version 
 * @className: UserInfoController <br/>
 * @date: 2016-5-30 下午3:21:03 <br/>
 * @since JDK 1.7
 *
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("/user/findAll")
	 public ModelAndView hello(String name) {
		return new ModelAndView("users").addObject("users", userService.findAll());
	 }

	@RequestMapping("/user/save")
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String status = request.getParameter("status");
		String name = request.getParameter("name");
		User u = new User();
		u.setId(status);
		u.setName(name);
		u.setIsDeleted(status);
		System.out.println(u.toString());
		Gson gson = new Gson();
		String a = gson.toJson(userService.save(u));
		out.print("返回内容为:" + a);
	}

	@RequestMapping("/user/updateNameById")
	 public ModelAndView updateNameById(String id, String name) {
		return new ModelAndView("users").addObject("users", userService.updateNameById(id, name));
	 }

	@RequestMapping("/user/del")
	 public ModelAndView del(String id) {
		return new ModelAndView("users").addObject("users", userService.del(id));
	 }
	
}
