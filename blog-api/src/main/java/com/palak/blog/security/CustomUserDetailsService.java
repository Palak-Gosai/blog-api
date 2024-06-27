package com.palak.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.palak.blog.entity.User;
import com.palak.blog.exception.ResouceNotFoundException;
import com.palak.blog.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from db by username
		User user = this.userRepo.findByEmail(username)
				.orElseThrow(() -> new ResouceNotFoundException("User", "email" + username, 0));

		// NOTE : you can not directly return user object coz return type is UserDetails
		// so we have to implement tht interface in User class and override its method
		// and add role collection and username.
		return user;
	}

}
