package com.dxc.socialmedia.services;

import java.util.List;
import java.util.stream.Collectors;

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
		
		return  list;

	}
	public UserData findByDataId(int dataId) {

		UserData list = dataRepo.findById(dataId) ;
		return list;

	}
	
	public List<UserData> sendAllData() {

		List<UserData> list =  (List<UserData>) dataRepo.findAll();
		List<UserData> filteredList = list.stream().filter(data -> data.getIsAvailable() ==0 ).collect(Collectors.toList());
		return filteredList;

	}
	
	public UserData insertData(UserData data) {
		return dataRepo.save(data);
		
	}
	
	public UserData deleteData(UserData data,int availability) {
		data.setIsAvailable(1); // delete
		return dataRepo.save(data);
		
	}
	public UserData updateData(UserData dbdata, UserData data) {
		// TODO Auto-generated method stub
		
		dbdata.setCaption(data.getCaption());// allow change caption only
		return dataRepo.save(dbdata); // edit db data return dbdata
		
	}
	
	
	

}
