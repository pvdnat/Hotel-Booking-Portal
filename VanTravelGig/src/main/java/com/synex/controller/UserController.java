package com.synex.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.synex.domain.User;
import com.synex.repository.UserRepository;
import com.synex.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller

@SessionAttributes("user")
public class UserController {
	
	@Autowired UserService userService;


	@GetMapping(value = "/login")
	public String login(@RequestParam(required = false) String logout, @RequestParam(required = false) String error,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
		String message = "";
		if (error != null) {
			message = "Invalid Credentials";
		}
		if (logout != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
			}
			message = "Logout";
			return "login";
		}
		model.addAttribute("Message", message);
		return "login";

	}

	@GetMapping(value = "/accessDeniedPage")
	public String accessDenied(Principal principal, Model model) {
		String message = principal.getName() + ", Unauthorised access";
		model.addAttribute("Message", message);
		return "accessDenied";

	}

	@GetMapping(value = "/signup")
	public String signup(@RequestParam String userEmail, @RequestParam String userName, @RequestParam String password) {
		return "login";

	}
	
	@GetMapping(value = "/user/{username}")
	@ResponseBody
	public String getUserByUsername(@PathVariable String username) {
		return userService.findByUserName(username).getEmail();

	}

	

	@GetMapping(value = "/username")
	@ResponseBody
	public String currentUserName(Authentication authentication) {
		return authentication.getName();
	}
	
	@RequestMapping(value = "/userProfile",method = RequestMethod.GET)
	public String userProfile(Principal principal) {
		return "userProfile";
	}
	

//	@Bean
//	public BCryptPasswordEncoder bCryptpeasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
