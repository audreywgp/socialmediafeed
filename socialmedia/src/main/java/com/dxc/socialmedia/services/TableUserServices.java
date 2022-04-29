package com.dxc.socialmedia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.socialmedia.controller.exception.UserNotFoundException;
import com.dxc.socialmedia.entity.TableUserDetails;
import com.dxc.socialmedia.repository.UserDetailsRepository;

@Service
public class TableUserServices{
	@Autowired
	private UserDetailsRepository detailsRepo;
	
	public List<TableUserDetails> sendUserList() {

		List<TableUserDetails> list = (List<TableUserDetails>) detailsRepo.findAll();
		return list;

	}

	public TableUserDetails getUserDetailsById(int userId) {

		TableUserDetails user = null;
		user = detailsRepo.findById(userId);
		if(user != null) {
			return user;
		}else {
			throw new UserNotFoundException("No user id found");
		}
	}

	public TableUserDetails getUsername(String username) {
		
		return detailsRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("service : No user name found"));
	}

	public TableUserDetails insertUser(TableUserDetails user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		return detailsRepo.save(user);
	}
	
	public TableUserDetails deleteUser(TableUserDetails user,int availablility) {
		user.setIsAvailable(availablility);
		return detailsRepo.save(user);
	
	}

	
	
	public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	

//	public getUserData(s) {
//		
//	}

}
