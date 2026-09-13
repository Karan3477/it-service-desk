package com.karan.itservicedesk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.karan.itservicedesk.dto.UserResponseDTO;
import com.karan.itservicedesk.model.User;
import com.karan.itservicedesk.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	
   public UserService(UserRepository userRepository) {
	   
	   this.userRepository = userRepository;
   }
   
   public User saveUser(User user) {
	   
	   return userRepository.save(user);
   }
   
   public List<User> findAllUsers(){
	   
	   return userRepository.findAll();
   }
   
   public User findUserById(Long id) {
	   
	   return userRepository.findById(id).orElse(null);
   }
   
   public UserResponseDTO convertToDTO(User user) {
	   UserResponseDTO dto = new UserResponseDTO();
	   
	   dto.setId(user.getId());
	   dto.setName(user.getName());
	   dto.setEmail(user.getEmail());
	   dto.setRole(user.getRole());
	   
	   return dto;
   }
   
   public User updateUser(Long id , User user) {
	 
	   User existingUser = userRepository.findById(id).orElse(null);
	   
	   if(existingUser == null) {
		   return null;
	   }
	   
	   existingUser.setName(user.getName());
	   existingUser.setEmail(user.getEmail());
	   existingUser.setPassword(user.getPassword());
	   existingUser.setRole(user.getRole());
	   
	   return userRepository.save(existingUser);
   }
   
   public boolean deleteUser(Long id) {
	   
	   if(!userRepository.existsById(id)) {
		   return false;
	   }
	   
	   userRepository.deleteById(id);
	   return true;
   }
   
   
   
   
   
}
