package com.dxc.socialmedia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dxc.socialmedia.entity.UserData;
import com.dxc.socialmedia.entity.UserDetails;

public interface UserDataRepository extends CrudRepository<UserData, Integer> {
	
	public UserData findById(int id);
	public List<UserData> findByUserId(int id);
	

}
