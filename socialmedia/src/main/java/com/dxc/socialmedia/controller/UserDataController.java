package com.dxc.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.socialmedia.entity.UserData;
import com.dxc.socialmedia.entity.UserDetails;
import com.dxc.socialmedia.services.UserDataService;
import com.dxc.socialmedia.services.UserServices;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/data")
public class UserDataController {
	
	@Autowired 
	private UserDataService dataServices;
	
//	@GetMapping("/")
//	public String home() {
//		return "welcome";
//
//	}
	
	@GetMapping("/get/{user_id}")
	public List<UserData> getUserData(@PathVariable("user_id") int userId ) {
		return dataServices.sendUserData(userId); // return a list [{}]

	}
	
	@GetMapping("/all")
	public List<UserData> getDataList() {
		return dataServices.sendAllData();
	}
	
	@PostMapping("/add") 
	public UserData addUserData(@RequestBody UserData data) {
		String filename = "assets\\videos\\" + data.getLocationPath();
		data.setLocationPath(filename);
		return dataServices.insertData(data);

	}

}
