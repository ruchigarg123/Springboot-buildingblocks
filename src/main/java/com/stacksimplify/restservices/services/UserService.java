package com.stacksimplify.restservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
//import com.sun.el.stream.Optional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if(existingUser!= null)
		{
			throw new UserExistsException("User already exists in repository"); 
		}
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		 Optional<User>   user  = userRepository.findById(id);
		 if(!user.isPresent())
		 {
			 throw new UserNotFoundException("User Not found in User Repository");
		 }
		return user;
	}
	
	
	public User updateUserById(Long id, User user) throws UserNotFoundException
	{
		  Optional<User> optionaluser = userRepository.findById(id);
		  if(!optionaluser.isPresent())
		 {
		    throw new UserNotFoundException("User Not found in User Repository, please provide correct id!!");
		 }
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id)
	{
		if(!userRepository.findById(id).isPresent())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not found,please provide valid user id");
			
		}
		
		userRepository.deleteById(id);
	}
	
	public User getUserByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
}
