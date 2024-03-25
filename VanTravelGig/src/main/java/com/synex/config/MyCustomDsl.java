package com.synex.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
	private boolean flag;

	@Override
	public void init(HttpSecurity http) throws Exception {
		// any method that adds another configurer
		// must be done in the init method
		
		http.csrf().disable();
	}

	public MyCustomDsl flag(boolean value) {
		this.flag = value;
		return this;
	}

	public static MyCustomDsl customDsl() {
		return new MyCustomDsl();
	}
}