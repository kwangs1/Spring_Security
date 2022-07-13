package com.spring.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.security.HomeController;

@Controller
public class LoginLogoutController {
	private static final Logger logger = LoggerFactory.getLogger(LoginLogoutController.class);
	
	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public void home() {
		
	}
}
