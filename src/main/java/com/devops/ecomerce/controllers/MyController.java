package com.devops.ecomerce.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.devops.ecomerce.models.*;

@RestController
public class MyController {
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> userList=new ArrayList();
		if(userList.isEmpty()){
			userList.add(new User());
		}
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
}
