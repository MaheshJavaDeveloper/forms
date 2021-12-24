package com.combis.app.service;

import java.util.List;

import com.combis.app.model.User;

public interface UserService {
	
	List<User> getAllUsers();
	User getUserByid(long id);
	boolean validateExistEmail(String email);
	User saveUser(User user);

}
