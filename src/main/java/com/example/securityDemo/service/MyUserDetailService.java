package com.example.securityDemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.securityDemo.entities.MyUser;
import com.example.securityDemo.repository.MyuserRepo;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	private MyuserRepo myuserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MyUser> user = myuserRepo.findByUsername(username);
		
		if(user.isPresent())
		{
			var userObj = user.get();
			
			return User.builder()
					.username(userObj.getUserName())
					.password(userObj.getPassword())
					.roles(getRoles(userObj))
					.build();
			
		}else
			throw new UsernameNotFoundException(username);
	}

	private String[] getRoles(MyUser userObj) {
		if(userObj.getRole()==null)
			return new String[] {("USER")};
		
		return  userObj.getRole().split(",");
		
	}

}
