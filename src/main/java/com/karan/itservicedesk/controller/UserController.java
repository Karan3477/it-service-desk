package com.karan.itservicedesk.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.karan.itservicedesk.dto.UserResponseDTO;
import com.karan.itservicedesk.model.User;
import com.karan.itservicedesk.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/api/users")
	public UserResponseDTO createUser(@Valid @RequestBody User user) {
		User savedUser = userService.saveUser(user);
		return userService.convertToDTO(savedUser);
	}
	
	@GetMapping("/api/users")
	public List<UserResponseDTO> getAllUsers() {
		return userService.findAllUsers()
				.stream()
				.map(userService::convertToDTO)
				.toList();
	}
	
	@GetMapping("/api/users/{id}")
	public UserResponseDTO getUserById(@PathVariable Long id) {
		User user = userService.findUserById(id);
		return userService.convertToDTO(user);
	}
	
	@PutMapping("/api/users/{id}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,@Valid @RequestBody User user) {
		
		User updateUser = userService.updateUser(id,user);
		
		if(updateUser == null ) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userService.convertToDTO(updateUser));
	}
	
	@DeleteMapping("/api/users/{id}")
		public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
			
			boolean deleted = userService.deleteUser(id);
			
			if(!deleted) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.noContent().build();
			
		}
	}
