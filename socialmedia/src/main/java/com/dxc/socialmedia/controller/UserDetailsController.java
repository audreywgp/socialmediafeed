package com.dxc.socialmedia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.dxc.socialmedia.entity.TableUserDetails;
import com.dxc.socialmedia.services.TableUserServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/user")
public class UserDetailsController {
	@Autowired
	private TableUserServices detailsService;
	private TableUserDetails user ;

//	private SecurityAuditorAware securityAuditAware = new SecurityAuditorAware();

	@GetMapping("/all") 
	public List<TableUserDetails> getUserList() {
		System.out.println("hello");
		return detailsService.sendUserList();
	}

	// login page

	@GetMapping("/login/{username}")
	public TableUserDetails getUserDetails(@PathVariable("username") String username) throws Exception {
		this.user = detailsService.getUsername(username);
		if(user.getIsAvailable() == 1) {
			throw  new Exception("user has been deleted");
		}else {
			return detailsService.getUsername(username);
			
		}
		
		
	}

	@GetMapping("/get/{id}")
	public TableUserDetails getUserDetails(@PathVariable("id") int id) throws Exception {
		this.user = detailsService.getUserDetailsById(id);
		if(user.getIsAvailable()==1) {
			throw  new Exception("user has been deleted");
		}else {
			return this.user;
		}
		
	}
	
	//sign up page
	@PostMapping("/adduser")
	public TableUserDetails addUser(@RequestBody TableUserDetails user) {

		System.out.println("hello");
		if (user.getRole().length() == 0) {
			user.setRole("user"); // default role
		}
		if (user.getPassword().length() == 0 || user.getUsername().length() == 0 || user.getName().length() ==0) {

			throw new InputNullException("one or more user input field empty");
		} else {
			detailsService.insertUser(user);
		}
//		securityAuditAware.setUsername(user.getUsername());
//		Optional<String> a = securityAuditAware.getCurrentAuditor();		
		return user;

	}

	@PostMapping("/{username}/deleteuser")
	public String deleteUser(@PathVariable("username") String username,@RequestBody TableUserDetails deleteuser) throws Exception {
		this.user = detailsService.getUsername(username);
//		System.out.println(deleteuser);
		TableUserDetails toDeleteUser = detailsService.getUsername(deleteuser.getUsername());
		//0 = available
		//1 not available
		if(user.getRole().equals("admin")) {
			if(toDeleteUser.getIsAvailable() ==0) {
				detailsService.deleteUser(toDeleteUser,1);
			}else {
				throw new UserNotFoundException(toDeleteUser.getUsername()+ " was already deleted");
			}
			
		}else {
			throw new Exception("only admin can");
		}
		
		return "deleted successfully";
	}
	
	
	

}
