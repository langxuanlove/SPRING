package com.gnet.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 */
@Configuration
@ComponentScan("com.gnet")
@EnableAutoConfiguration 
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setWebEnvironment(true);
		app.setShowBanner(false);
		Set<Object> set = new HashSet<Object>();
		//添加xml文件的地方.
		// set.add("classpath:applicationContext.xml");
		set.add("classpath:servlet.xml");
		app.setSources(set);
		app.run(args);
	}
}
