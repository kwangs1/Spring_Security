package com.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginLogoutController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(LoginLogoutController.class);
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public void home() {
		
	}
}
