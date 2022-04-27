package com.dxc.socialmedia.controller.exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String errorMsg) {
		super(errorMsg);
	}
	

}
