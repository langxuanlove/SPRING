package com.gnet.module.test.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.gnet.module.test.service.ITestService;
import com.gnet.utils.RestfulUtil;

@Controller
@RequestMapping("/testController")
public class TestController {
	@Resource(name = "testService")
	private ITestService testService;
	
	public TestController() {
		System.out.println("初始化成功。。。。。");
	}

	@RequestMapping(value = "/getTasks", method = RequestMethod.GET)
	public void getTasks(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String resus = RestfulUtil.sendHttpRequest("http://192.168.4.52:8080/GnetBMS/services/Gnet_WebServiceRest/registerEvent", "123");
		System.out.println(resus);
		List<Map<String, Object>> _list = testService.getTaskAll();
		Object json = JSON.toJSON(_list);
		PrintWriter out = response.getWriter();
		out.print(json);
	}
	
	@RequestMapping(value = "/updateTest", method = {RequestMethod.POST,RequestMethod.GET})
	public void updateTest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			testService.updateTest();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/getAllInfo", method = {RequestMethod.POST,RequestMethod.GET})
	public void getAllInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println(request.getParameter("key"));
		try{
			List<Map<String, Object>> _list = testService.getTaskAll();
			Object json = JSON.toJSON(_list);
			PrintWriter out = response.getWriter();
			out.print(json);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
