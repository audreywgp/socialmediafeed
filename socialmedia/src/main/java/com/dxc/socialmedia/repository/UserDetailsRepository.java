package com.dxc.socialmedia.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dxc.socialmedia.entity.UserDetails;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Integer> {
	
	public UserDetails findById(int id);
	public Optional<UserDetails> findByUsername(String username);

}