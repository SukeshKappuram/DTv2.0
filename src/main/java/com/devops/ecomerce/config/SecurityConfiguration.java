package com.devops.ecomerce.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  /*auth.inMemoryAuthentication().withUser("me").password("123456").roles("USER");
	  auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
	  auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");*/
	  System.out.println("data source:"+dataSource);
	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select mailid,password,enabled from user where mailid=?")
		.authoritiesByUsernameQuery(
			"select u1.mailid, u2.rolename from user u1, userrole u2 where u1.id = u2.roleid_id and u1.mailid =?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/User/**").access("hasRole('ROLE_USER')")
		.and().formLogin().defaultSuccessUrl("/").failureUrl("/log?error")
		.and().exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests()
		.antMatchers("/Seller/**").access("hasRole('ROLE_SELLER')")
		.and().formLogin().defaultSuccessUrl("/Seller/sell").failureUrl("/log?error")
		.and().exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests()
		.antMatchers("/Admin/**").access("hasRole('ROLE_ADMIN')")
		.and().formLogin().defaultSuccessUrl("/Admin/").failureUrl("/log?error")
		.and().exceptionHandling().accessDeniedPage("/403");
		
		http.headers().frameOptions().disable();
	}
}
