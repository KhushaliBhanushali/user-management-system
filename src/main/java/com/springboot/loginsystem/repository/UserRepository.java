package com.springboot.loginsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.loginsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);

	boolean existsByEmail(String email);
}
