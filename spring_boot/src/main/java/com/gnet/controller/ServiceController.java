package com.gnet.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnet.model.RedisUtil;
import com.gnet.model.User;
import com.gnet.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 
 * @author Key
 * 
 */
@RestController
@RequestMapping("/service")
public class ServiceController {
	@Resource(name = "service")
	private ServiceImpl service;
	private Logger logger=Logger.getLogger(ServiceController.class); 
	@RequestMapping("/{id}")
	public void view(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		User user = new User();
		user.setId(Integer.valueOf(id));
		user.setName("Linda");
		user.setId(250.0);
		user.setName("测试人民");
		PrintWriter out = response.getWriter();
		out.print(user.getName());
	}
	@RequestMapping("/redisQuery")
	public void redisQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String key=request.getParameter("key");
		logger.info("方法redisQuery请求key为:"+key);
		String str=RedisUtil.getInstance().getRedisMsg(key);
		logger.info("方法redisQuery返回value为:"+str);
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(str));
	}
	
	@RequestMapping("/redisInsert")
	public void redisInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String key=request.getParameter("key");
		String value=request.getParameter("value");
		logger.info("方法redisInsert插入的key为:"+key+";value为:"+value);
		String str=RedisUtil.getInstance().setKeyValue(key,value);
		logger.info("方法redisInsert返回结果为:"+str);
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(str));
	}
}
