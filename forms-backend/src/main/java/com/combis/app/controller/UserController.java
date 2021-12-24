package com.combis.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.combis.app.model.User;
import com.combis.app.service.impl.UserServiceImpl;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping("/users")
	private List<User> getUser() {
		return userServiceImpl.getAllUsers();
	}
	
	@GetMapping("/email/{email}")
	private boolean getByEmail(@PathVariable(value = "email") String email) {
		
		return userServiceImpl.validateExistEmail(email);
	}
	
	@GetMapping("/{id}")
	private User getUserById(@PathVariable(value = "id") long id) {
		System.out.println(id);
		return userServiceImpl.getUserByid(id);
	}
	
	@PostMapping("/users")
	public User saveUser(@RequestBody User user) {
		
		System.out.println(user);
		return userServiceImpl.saveUser(user);
	}

}
