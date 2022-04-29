package com.dxc.socialmedia.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dxc.socialmedia.entity.TableUserDetails;

@Repository
public interface UserDetailsRepository extends CrudRepository<TableUserDetails, Integer> {
	
	public TableUserDetails findById(int id);
	public Optional<TableUserDetails> findByUsername(String username);

}
