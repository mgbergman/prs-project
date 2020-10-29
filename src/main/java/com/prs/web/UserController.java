package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import com.prs.db.UserRepo;

import com.prs.business.User;

@CrossOrigin
@RestController
@RequestMapping("/api/users")

public class UserController {

	@Autowired
	private UserRepo userRepo;
	
	//Get All Users
	@GetMapping("")
	public List <User> getAllUsers() {
		return userRepo.findAll();
	}
	
	//Validate User
	@GetMapping("/{userName}/{password}")
	public User getAllUsersByUserNamePassword(@PathVariable String userName,
														@PathVariable String password) {
		User u = userRepo.findByUserNameAndPassword(userName, password);
		if (u != null) {
			return u;
			
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");	
		}		
	}
	
	//Add User
	@PostMapping("")
	public User addUser(@RequestBody User u) {
		return userRepo.save(u);
	}
	
	//Get User By Id
	@GetMapping("/{id}")
	public Optional <User> getUser(@PathVariable int id){
		Optional <User> u = userRepo.findById(id);
		if (u.isPresent()) {
			return u;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
	}
	
	//Update User 
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User u, @PathVariable int id) {
		if (id==u.getId()) {
		return userRepo.save(u);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");	
		}
	}
	
	//Delete User
	@DeleteMapping("/{id}")
	public Optional <User> deleteUser(@PathVariable int id) {
		Optional <User> u = userRepo.findById(id);
		if (u.isPresent()) {
			userRepo.deleteById(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found");
		}
		return u;
	}

}
