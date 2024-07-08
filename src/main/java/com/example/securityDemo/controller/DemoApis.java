package com.example.securityDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class DemoApis {
	
	@GetMapping("/home")
	public String handlingHomePage()
	{
		return "On Home";
	}
	@GetMapping("/admin/home")
	public String handingAdminHomePage()
	{
		return "On admin Home";
	}
	@GetMapping("/user/home")
	public String HandlingUserHomePage()
	{
		return "On user Home";
	}
//	  @GetMapping("/login/1")
//	    public String login() {
//	        return "login.html"; // This will render src/main/resources/templates/login.html
//	    }

}
