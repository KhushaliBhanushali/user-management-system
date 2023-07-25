package com.springboot.loginsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.loginsystem.entity.User;
import com.springboot.loginsystem.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> listAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User loadUserById(int id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " Not Found"));
	}

	@Override
	public User createOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getUserByEmailandPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email,password);	
	}

	@Override
	public boolean checkEmailExist(String email) {
		return userRepository.existsByEmail(email);
	}

}
