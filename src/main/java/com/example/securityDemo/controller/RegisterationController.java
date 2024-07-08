package com.example.securityDemo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.securityDemo.entities.MyUser;
import com.example.securityDemo.repository.MyuserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class RegisterationController {
	@Autowired
	private MyuserRepo myuserRepo;

	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/register/user")
	public MyUser createUser(@RequestBody MyUser myUser) {

		myUser.setPassword(encoder.encode(myUser.getPassword()));

		myuserRepo.save(myUser);
		return myUser;
	}

}
