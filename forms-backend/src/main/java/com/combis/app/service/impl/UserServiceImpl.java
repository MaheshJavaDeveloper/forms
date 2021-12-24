package com.combis.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.combis.app.model.User;
import com.combis.app.repo.UserRepo;
import com.combis.app.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User getUserByid(long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return new User();
		}

	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		if(!validateExistEmail(user.getEmail())) {
			return userRepo.save(user);	
		}
		return null;
	}

	@Override
	public boolean validateExistEmail(String email) {
		
		Optional<User> user = userRepo.findByEmail(email);
		if (user.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

}
