package com.example.securityDemo.security;

public class LoginResponse {

	  private String username;
	  private String message;
	  
	  
	public LoginResponse() {
		super();
	}
	public LoginResponse(String username, String message) {
		super();
		this.username = username;
		this.message = message;
	}
	@Override
	public String toString() {
		return "LoginResponse [username=" + username + ", message=" + message + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	  
	  
}
