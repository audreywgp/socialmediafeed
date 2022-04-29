package com.dxc.socialmedia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.socialmedia.entity.UserData;
import com.dxc.socialmedia.repository.UserDataRepository;

@Service
public class UserDataService {
	@Autowired
	private UserDataRepository dataRepo;
	
	public List<UserData> sendUserData(int userId) {

		List<UserData> list =  (List<UserData>) dataRepo.findByUserId(userId);
		return list;

	}
	
	public List<UserData> sendAllData() {

		List<UserData> list =  (List<UserData>) dataRepo.findAll();
		return list;

	}
	
	public UserData insertData(UserData data) {
		return dataRepo.save(data);
		
	}
	

}
