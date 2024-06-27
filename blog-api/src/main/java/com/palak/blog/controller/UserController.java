package com.palak.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.palak.blog.payloads.APIResponse;
import com.palak.blog.payloads.UserDto;
import com.palak.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	
	@Autowired
	UserService userService;
	
	
	//create User
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createdUser = userService.createUser(userDto);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
		
		
	}
	
	//update user
	@PutMapping("/{userid}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userid") int userid){
		
		UserDto updatedUser = userService.updateUser(userDto, userid);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	
	//get all user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//get user by id
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userid") int userid){
		return ResponseEntity.ok(this.userService.getUserById(userid));
	}
	
	//delete user
	@DeleteMapping("/{userid}")
	public ResponseEntity<APIResponse> deleteUser(@PathVariable("userid") int userid){
		
		userService.deleteUser(userid);
		return new ResponseEntity(new APIResponse("user deleted Successfully",true),HttpStatus.OK);
		
	}
}
