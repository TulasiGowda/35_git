package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaexpress.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

//	DSL(Domain Specific Language)
	
	User findByUserName(String userName);
	
	User findByPassword(String password);
	
	User findByuserNameAndPassword(String userName, String password);
	
	User findByuserNameOrPassword(String userName, String password);
}
