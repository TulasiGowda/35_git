package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	@ResponseStatus(code=HttpStatus.CREATED)
	public void createUser(@RequestBody User user) {
		log.info("UserController :: createUser {}", user);
		userService.createUser(user);
		log.info("User created successfully and send to service layer");
	}

	@GetMapping("{userId}")
	public User getUserById(@PathVariable Long userId) {
		log.info("UserController :: getUserById {}", userId);
		return userService.findByUserId(userId);
	}

	@PutMapping("{userId}")
	public void updateUser(@PathVariable Long userId, @RequestBody User user) {
		log.info("UserController :: updateUser {} {}", userId, user);
		userService.updateUser(userId, user);
		log.info("User updated successfully and send to service layer ");
	}
	
	@PatchMapping("changePwd/{userId}")
	public void updatePassword(@PathVariable Long userId, @RequestBody User user) {
		log.info("UserController :: updatePassword {} {}", userId, user);
		userService.updatePassword(userId, user);
		log.info("Password updated successfully and send to service layer");
	}

	@DeleteMapping("{userId}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long userId) {
		log.info("UserController :: deleteUser {}", userId);
		userService.deleteUser(userId);
		log.info("User deleted sucessfully and send to service layer");
	}
}
