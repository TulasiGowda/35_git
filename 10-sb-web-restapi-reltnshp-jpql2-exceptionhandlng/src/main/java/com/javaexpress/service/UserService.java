package com.javaexpress.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.User;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void createUser(User user) {
		log.info("UserService :: createUser {} {}", user.getUserName(), user.getEmail());
		userRepository.save(user);
		log.info("User successfully saved data in DB");
	}

	public List<User> fetchAllUsers() {
		log.info("UserService :: fetchAllUsers");
		List<User> userList = userRepository.findAll();
		List<User> result = userList.stream().sorted(Comparator.comparing(User::getUserName)).toList();
		return result;
	}

	public User findByUserId(Long userId) {
		log.info("UserService :: findByUserId {}", userId);
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
	}

	public void updateUser(Long userId, User inputUser) {
		log.info("UserService :: updateUser {} {}", userId, inputUser);
		User dbUser = findByUserId(userId);
		dbUser.setUserName(inputUser.getUserName());
		dbUser.setPassword(inputUser.getPassword());
		dbUser.setEmail(inputUser.getEmail());
		userRepository.save(dbUser);
		log.info("User updated successfully to DB");
	}

	public void updatePassword(Long userId, User inputUser) {
		log.info("UserService :: updatePassword {} {}", userId, inputUser);
		User dbUser = findByUserId(userId);
		dbUser.setPassword(inputUser.getPassword());
		userRepository.save(dbUser);
		log.info("Password is updated based on userId sucessfully to DB");
	}

	public void deleteUser(Long userId) {
		log.info("UserService :: deleteUser {}", userId);
		if (userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
		} else {
			throw new RuntimeException("User Not Found");
		}
		log.info("user deleted succesfully from DB");
	}

	public void deleteUser_Another(Long userId) {
		log.info("UserService :: deleteUser_Another {}", userId);
		User dbUser = findByUserId(userId);
		userRepository.delete(dbUser);
		log.info("user deleted from DB");
	}
}
