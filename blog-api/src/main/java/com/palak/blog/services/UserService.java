package com.palak.blog.services;
import java.util.List;

import com.palak.blog.payloads.*;

public interface UserService {

	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,int userId);
	UserDto getUserById(int userId);
	List<UserDto> getAllUsers();
	void deleteUser(int userId);
	
	
}
