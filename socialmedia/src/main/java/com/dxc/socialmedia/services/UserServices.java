package com.dxc.socialmedia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.socialmedia.controller.exception.UserNotFoundException;
import com.dxc.socialmedia.entity.UserDetails;
import com.dxc.socialmedia.repository.UserDetailsRepository;

@Service
public class UserServices{
	@Autowired
	private UserDetailsRepository detailsRepo;
	
	public List<UserDetails> sendUserList() {

		List<UserDetails> list = (List<UserDetails>) detailsRepo.findAll();
		return list;

	}

	public UserDetails getUserDetailsById(int userId) {

		UserDetails user = null;
		user = detailsRepo.findById(userId);
		if(user != null) {
			return user;
		}else {
			throw new UserNotFoundException("No user id found");
		}
		
	}

	public UserDetails getUsername(String username) {
		return detailsRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("No user name found"));
	}

	public UserDetails insertUser(UserDetails user) {
		return detailsRepo.save(user);
	}

	

//	public getUserData(s) {
//		
//	}

}
