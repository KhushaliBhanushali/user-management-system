package com.springboot.loginsystem.service;

import java.util.List;

import com.springboot.loginsystem.entity.User;

public interface UserService {

	List<User> listAllUsers();
	
	User loadUserById(int id);
	
	User createOrUpdateUser(User user);
	
	void deleteUser(int id);
	
	User getUserByEmailandPassword(String email, String password);
	
    boolean checkEmailExist(String email);
}
