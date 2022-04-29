package com.dxc.socialmedia.entity;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityAuditorAware implements AuditorAware<String> {
	
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	//createdby and updateby null no username
	public Optional<String> getCurrentAuditor() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // auth now is annoymous @ web config
		if(name.equals("anonymousUser"))
		{
			name="user";
		}

		return Optional.ofNullable(name).filter(data -> !data.isEmpty());
	}
}


