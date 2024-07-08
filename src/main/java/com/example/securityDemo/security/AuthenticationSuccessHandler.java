package com.example.securityDemo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{

	 @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
	        boolean isAdmin = authentication.getAuthorities().stream()
	                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
	        if (isAdmin) {
	            setDefaultTargetUrl("/admin/home");
	        } else {
	            setDefaultTargetUrl("/user/home");
	        }
	        super.onAuthenticationSuccess(request, response, authentication);
	    }
//	     // Create a custom response object with relevant data
//	        LoginResponse loginResponse = new LoginResponse();
//	        loginResponse.setUsername(authentication.getName());
//	        loginResponse.setMessage("Login successful!");
//
//	        // Set the response content type (e.g., JSON, XML)
//	        response.setContentType("application/json");
//
//	        // Use an appropriate method (e.g., ObjectMapper for JSON) to serialize the response object
//	        ObjectMapper mapper = new ObjectMapper();
//	        mapper.writeValue(response.getOutputStream(), loginResponse);
//	    }
		
}
