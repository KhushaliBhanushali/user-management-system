package com.springboot.loginsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.loginsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmailAndPassword(String email, String password);

	boolean existsByEmail(String email);
}
