package com.example.securityDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.securityDemo.entities.MyUser;

public interface MyuserRepo extends JpaRepository<MyUser, Long>{
	
	Optional<MyUser> findByUsername(String username);

}
