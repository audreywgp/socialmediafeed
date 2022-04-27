package com.dxc.socialmedia.entity;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
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
		// Just return a string representing the username
//		Authentication authentication = SecurityContextHolder.getContext()
//															.getAuthentication();
//
//		if (authentication == null || !authentication.isAuthenticated()) {
//			return null;
//		}

//		return  ((SecurityAuditorAware) authentication.getPrincipal()).getUsername();
//		return Optional.ofNullable(((SecurityAuditorAware) authentication.getPrincip)).getUsername()).filter(data -> !data.isEmpty());

		return Optional.ofNullable(this.username).filter(data -> !data.isEmpty());
	}
}


