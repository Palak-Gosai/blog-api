package com.palak.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palak.blog.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	

	Optional<User> findByEmail(String email);
	
}