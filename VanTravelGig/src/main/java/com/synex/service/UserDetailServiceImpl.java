
package com.synex.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.synex.domain.Role;
import com.synex.domain.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		Set<GrantedAuthority> ga = new HashSet<>();
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			System.out.println("role.getRoleName()" + role.getRoleName());
			ga.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), ga);
	}

}
