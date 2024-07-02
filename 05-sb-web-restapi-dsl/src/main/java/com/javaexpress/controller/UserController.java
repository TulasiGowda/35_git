package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.entities.User;
import com.javaexpress.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public void createUser(@RequestBody User user) {
		log.info("UserController :: createUser {}", user.getUserName());
		userService.createUser(user);
		log.info("createUser successfully send to service");
	}
	
	@GetMapping("{userId}")
	public User getUserById(@PathVariable Long userId) {
		log.info("UserController :: getUserById");
		log.info("getUserById successfully send to service");
		return userService.findUserById(userId);
		
	}
	
	@PutMapping("{userId}")
	public void updateUser(@PathVariable Long userId, @RequestBody User user) {
		log.info("UserController :: updateUser");
		userService.updateUser(userId, user);
	}
	
	@PatchMapping("changePw/{userId}")
	public void updatePassword(@PathVariable Long userId, @RequestBody User user) {
		log.info("UserController :: updateUser");
		userService.updatePassword(userId, user);
	}
	
	@DeleteMapping("{userId}")
	public void deleteUser(@PathVariable Long userId) {
		log.info("UserController :: deleteUser");
		userService.deleteUser(userId);
	}
	
	@GetMapping("{userName}")
	public User fectchUserByUserName(@PathVariable String userName) {
		log.info("UserController :: fectchUserByUserName {} ", userName);
		return userService.fetchByUserName(userName);
	}
	
	@GetMapping("{password}")
	public User fetchUserByPassword(@PathVariable String password) {
		log.info("UserController :: fetchUserByPassword {} ", password);
		return userService.fetchByPassword(password);
	}
	
	@GetMapping("/login/{userName}/{password}")
	public User fetchUserByuserNameAndPassword(@PathVariable String userName, @PathVariable String password) {
		log.info("UserController :: fetchUserByuserNameAndPassword {} ", userName,password);
		return userService.fetchByuserNameAndPassword(userName, password);
	}
	
	@GetMapping("/log/{userName}/{password}")
	public User fetchUserByuserNameOrPassword(@PathVariable String userName, @PathVariable String password) {
		log.info("UserController :: fetchUserByuserNameOrPassword {} ", userName,password);
		return userService.fetchByuserNameOrPassword(userName, password);
	}
}
