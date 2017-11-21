package com.it13.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {
	
	@RequestMapping("/demo/hello")
	 public ModelAndView hello(String name) {
		
		return new ModelAndView("demo").addObject("name", name);
	 }
	
}
