package com.example.securityDemo.security;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userService;
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(registery->{
					registery.requestMatchers("/home","/register/**").permitAll();
					registery.requestMatchers("/admin/**").hasRole("ADMIN");
					registery.requestMatchers("/user/**").hasRole("USER");
					
					registery.anyRequest().authenticated();
				})
//				.formLogin(formLogin -> formLogin.permitAll())
//						.defaultSuccessUrl("/home"))
					
				
				 .formLogin(httpSecurityFormLoginConfigurer -> {
	                    httpSecurityFormLoginConfigurer
	                            //.loginPage("/login/1")
	                            .successHandler(new AuthenticationSuccessHandler())
	                            .permitAll();
	                })
	                .build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder ()
	{
		return new BCryptPasswordEncoder();
	}
	 @Bean
		public UserDetailsService userDetailsService()
		{
		 	return userService;
		}
	 @Bean
	 public AuthenticationProvider authenticationProvider()
	 {
		 DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		 
		 authenticationProvider.setUserDetailsService(userService);
		 authenticationProvider.setPasswordEncoder(passwordEncoder());
		 return authenticationProvider;
	 }
/*	for inmemory user
  @Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails normalUser = User.builder()
				.username("shivam")
				.password("$2a$12$sgrywmp.OCwBFbTMIOs.uu5p69uTZ3rIFZhdKWfXZc3czM7dRIVd2")
				.roles("USER")
				.build();
		UserDetails adminUser = User.builder()
				.username("admin")
				.password("$2a$12$sfW2VtptEeiJ91JWV7MLy.qh3tvoqhei1wFaM5KXCTqyy/uflh/8S")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(normalUser,adminUser);
		
	}
*/
}
