package com.javaexpress.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.User;
import com.javaexpress.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void createUser(User user){
		log.info("UserService :: createUser {} {}", user.getEmail(), user.getUserName());
		userRepository.save(user);
		log.info("User successfully saved to DB");
	}
	
	public List<User> fetchALlUsers(){
		log.info("UserService :: fetchALlUsers {}");
		List<User> userList =  userRepository.findAll();
		List<User> result = userList.stream().sorted(Comparator.comparing(User :: getUserName)).toList();
		log.info("All User fetched from DB");
		return result;
		
	}
	
	public User findUserById(Long userId){
		log.info("UserService :: findUserById ");
		return userRepository.findById(userId)
			.orElseThrow(() -> new RuntimeException("User not found"));
		
	}
	
	public void updateUser(Long userId, User inputUser){
		log.info("UserService :: updateUser");
		User dbUser = findUserById(userId);
		dbUser.setEmail(inputUser.getEmail());
		dbUser.setUserName(inputUser.getUserName());
		dbUser.setPassword(inputUser.getPassword());
		userRepository.save(dbUser);
		log.info("User updated based on userId");
	}
	
	public void updatePassword(Long userId, User inputUser){
		log.info("UserService :: updateUser");
		User dbUser = findUserById(userId);
		dbUser.setPassword(inputUser.getPassword());
		userRepository.save(dbUser);
		log.info("User Password updated based on userId");
	}
	
	public void deleteUser(Long userId){
		log.info("UserService :: deleteUser");
		if(userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
		}
		else {
			throw new RuntimeException("User not found");	
		}
		log.info("User is deleted using userId");
	}
	
	public void deleteUser_another(Long userId){
		log.info("UserService :: deleteUser");
		User dbUser = findUserById(userId);
		userRepository.delete(dbUser);
		log.info("User is deleted using userId_anotherapproach");
	}
	
	public User fetchByUserName(String userName) {
		log.info("UserService :: fetchByUserName");
		return userRepository.findByUserName(userName);
	}
	
	public User fetchByPassword(String password) {
		log.info("UserService :: fetchByPassword");
		return userRepository.findByPassword(password);
	}
	
	public User fetchByuserNameAndPassword(String userName, String password) {
		log.info("UserService :: fetchByuserNameAndPassword");
		return userRepository.findByuserNameAndPassword(userName, password);
	}
	
	public User fetchByuserNameOrPassword(String userName, String password) {
		log.info("UserService :: fetchByuserNameOrPassword");
		return userRepository.findByuserNameOrPassword(userName, password);
	}
}
