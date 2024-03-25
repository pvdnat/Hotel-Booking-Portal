package com.synex.controller;


import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public String home(Principal principal) {
		return "Home";
	}
	
}
