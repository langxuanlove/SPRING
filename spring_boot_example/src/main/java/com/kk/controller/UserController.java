package com.gnet.controller;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnet.model.User;
import com.gnet.service.impl.ServiceImpl;
import com.google.gson.Gson;
/**
 * 
 * @author Key
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Resource(name="service")
	private ServiceImpl service;
    @RequestMapping("/{id}")
    public void view(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") String id) throws Exception {
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out=response.getWriter();
      User user=service.getUser();
      Gson gson=new Gson();
      String a=gson.toJson(user);
      out.print("汉字内容乱码.输入ID:"+id+";名称:"+user.getName()+";"+a);
    }
}

