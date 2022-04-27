package com.dxc.socialmedia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.socialmedia.controller.exception.InputNullException;
import com.dxc.socialmedia.controller.exception.UserNotFoundException;
import com.dxc.socialmedia.entity.SecurityAuditorAware;
import com.dxc.socialmedia.entity.UserDetails;
import com.dxc.socialmedia.services.UserServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/user")
public class UserDetailsController {
	@Autowired
	private UserServices detailsService;
//	private SecurityAuditorAware securityAuditAware = new SecurityAuditorAware();

	@GetMapping("/all") // get post or delete mapping
	public List<UserDetails> getUserList() {
		return detailsService.sendUserList();

	}

	// get data with username
	@GetMapping("/login/{username}")
	public UserDetails getUserDetails(@PathVariable("username") String username) {
		return detailsService.getUsername(username);
	}
	
	@GetMapping("/get/{id}")
	public UserDetails getUserDetails(@PathVariable("id") int id) {
		return detailsService.getUserDetailsById(id);
	}

	@PostMapping("/adduser")
	public UserDetails addUser(@RequestBody UserDetails user) {
		if (user.getRole().length() == 0) {
			user.setRole("user"); // default role
		}
		if (user.getPassword().length() == 0 || user.getUsername().length() == 0) {
			
			throw new InputNullException("one or more user input field empty");
		} else {
			detailsService.insertUser(user);
		}
//		securityAuditAware.setUsername(user.getUsername());
//		Optional<String> a = securityAuditAware.getCurrentAuditor();		
		return user;

	}

}
