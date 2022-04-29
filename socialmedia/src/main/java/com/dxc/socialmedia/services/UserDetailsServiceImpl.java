package com.dxc.socialmedia.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dxc.socialmedia.entity.MyUserDetails;
import com.dxc.socialmedia.entity.TableUserDetails;
import com.dxc.socialmedia.repository.UserDetailsRepository;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserDetailsRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        TableUserDetails user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Could not find user"));
        
        return new MyUserDetails(user);
    }
 
}