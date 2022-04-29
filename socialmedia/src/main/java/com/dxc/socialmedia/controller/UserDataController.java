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
import com.dxc.socialmedia.entity.TableUserDetails;
import com.dxc.socialmedia.services.UserDataService;
import com.dxc.socialmedia.services.TableUserServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/data")
public class UserDataController {

	@Autowired
	private UserDataService dataServices;

	@Autowired
	private TableUserServices detailsService;
	private TableUserDetails user;

	@GetMapping("/get/{user_id}")
	public List<UserData> getUserData(@PathVariable("user_id") int userId) {
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

	@PostMapping("/{username}/delete")
	public String deleteUserData(@PathVariable("username") String username, @RequestBody UserData dataid)
			throws Exception {

		this.user = detailsService.getUsername(username);
		UserData data = dataServices.findByDataId(dataid.getDataId());
		TableUserDetails dataUser = detailsService.getUserDetailsById(data.getUserId());

		if (this.user.getRole().equals("admin") || dataUser.getUsername().equals(username)) {
			if (data.getIsAvailable() == 0) {
				dataServices.deleteData(data, 1);// delete
				return "data delete successful";
			} else {
				throw new Exception("data has been deleted");

			}

		} else {
			throw new Exception("no Permission");
		}
	}

	@PostMapping("/{username}/update")
	public String updateUserData(@PathVariable("username") String username, @RequestBody UserData data) throws Exception {
		
		this.user = detailsService.getUsername(username);
		UserData dbData = dataServices.findByDataId(data.getDataId());
		System.out.println("account is :" +this.user.getName().toString());
		System.out.println(data.getDataId());
		
		//datauserid == username's userid or role = admin
		if(this.user.getRole().equals("admin")|| this.user.getUserId() == data.getUserId()) {
			if(data.getIsAvailable()== 0 ) {
				dataServices.updateData(dbData, data);//delete
				return"data update successful";
			}else {
				return "data dont exist";
				
			}
				
		}else {
			throw new Exception("no Permission");
		}
	}

}
