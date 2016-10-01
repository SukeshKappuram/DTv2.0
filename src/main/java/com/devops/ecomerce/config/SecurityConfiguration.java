package com.devops.ecomerce.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfiguration{
	
}
/*
@Configuration
@EnableWebSecurity
	public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
		@Autowired
		DataSource dataSource;
		
		@Autowired
		public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	             System.out.println("Inside the configauthentication");
	             System.out.println("data source:"+dataSource);
		  auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery(
				"select mailid,password from user where mailid=?")
			.authoritiesByUsernameQuery(
				"select u1.mailid, u2.rolename from user u1, userrole u2 where u1.id = u2.roleid_id and u1.mailid =?");
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			 System.out.println("Inside the configure");
		  http.authorizeRequests()
			.antMatchers("/User/**").access("hasRole('ROLE_USER')")
			.and()
			  .formLogin().loginPage("/login").loginProcessingUrl("/loginPattern").defaultSuccessUrl("/User").failureUrl("/login?error").usernameParameter("username").passwordParameter("password")
			.and()
			  .logout().logoutSuccessUrl("/log?logout")
			.and()
			  .exceptionHandling().accessDeniedPage("/403")
			.and()
			  .csrf();
		  
		  http.authorizeRequests()
			.antMatchers("/Admin/**").access("hasRole('ROLE_ADMIN')")
			.and()
			  .formLogin().loginPage("/login").loginProcessingUrl("/loginPattern").defaultSuccessUrl("/Admin").failureUrl("/login?error").usernameParameter("username").passwordParameter("password")
			.and()
			  .logout().logoutSuccessUrl("/log?logout")
			.and()
			  .exceptionHandling().accessDeniedPage("/403")
			.and()
			  .csrf();
		  
		  System.out.println("endof configure");
		}
}*/
