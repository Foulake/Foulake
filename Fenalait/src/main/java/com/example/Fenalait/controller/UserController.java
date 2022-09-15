package com.example.Fenalait.controller;

import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.Fenalait.dto.UserRequestDto;
import com.example.Fenalait.dto.UserResponseDto;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.model.Role;
import com.example.Fenalait.model.User;
import com.example.Fenalait.repository.UserRepository;
import com.example.Fenalait.service.UserService;
import com.example.Fenalait.utils.AppConstants;



@RestController
@RequestMapping("/api/users")

public class UserController {
	
	 @Autowired private UserRepository repo;

	 @Autowired private UserService userService;
	 
		 
	 @PostMapping("{email}/role/{roleName}")
	 public ResponseEntity<?> addRoleToUser(@PathVariable("email") String email, @PathVariable("roleName") String roleName){
	User user = userService.addRoleToUser(email, roleName);
		return ResponseEntity.ok(user);
	 }

	 @PostMapping("role")

	 public ResponseEntity<Role> createRole(@RequestBody Role role){
		 Role saveRole = userService.saveRole(role);
		 
		 URI roleURI = URI.create("/roles/" + saveRole.getId());

	        return ResponseEntity.created(roleURI).body(saveRole);
	 }
	 
	 
	  @GetMapping("/get/profile")
	    public ResponseEntity<User> getUserByProfile(Principal principal){
	    	User user = repo.findByEmail(principal.getName())
	    		.orElseThrow(() -> new ResourceNotFoundException("Il n'existe pas un Utilisateur avec E-mail :" +principal.getName()));
	    	return ResponseEntity.ok(user);
	    }
	 
	    @GetMapping("/getAllUsers")
	    public UserResponseDto getAllUsers(
	            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
	            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
	    ){
	        return userService.getAllUsers(pageNo, pageSize, sortBy, sortDir);
	    }
	    
	    @GetMapping("/get/{id}")
	  
	    public ResponseEntity<User> getUserById(@PathVariable Long id){
	    	User user = repo.findById(id)
	    		.orElseThrow(() -> new ResourceNotFoundException("Il n'existe pas un Utilisateur avec id :" +id));
	    	return ResponseEntity.ok(user);
	    }
	    
	    @PutMapping("/edit/{id}")
	    public ResponseEntity<User> updateUser(@Valid @RequestBody User userDetails, @PathVariable Long id){
	    	User user = repo.findById(id)
	    			.orElseThrow(()-> new ResourceNotFoundException(" Il n'existe pas un Utilisateur avec ID :" +id));
	    	
	    	PasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(userDetails.getPassword()));
	    	user.setEmail(userDetails.getEmail());
	    	user.setRoles(user.getRoles());
	    	user.setNom(userDetails.getNom());
	    	user.setPrenom(userDetails.getPrenom());	    	
	    	
	    	User updateUser = repo.save(user);
			return ResponseEntity.ok(updateUser);
	    }
	    
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
	    	User user = repo.findById(id)
	        		.orElseThrow(() -> new ResourceNotFoundException("Il n'existe pas un Utilisateur avec id :" +id));
	    	repo.delete(user);
	    		
	        	Map<String, Boolean> response =new  HashMap<>();
	        	response.put("delete", Boolean.TRUE);
	        	return ResponseEntity.ok(response); 
	    }
	    
	    /** Serach **/
		@GetMapping("/search/{keywords}")
		public  ResponseEntity<List<UserRequestDto>> searchUserByNom(@PathVariable("keywords") String keywords){
			List<UserRequestDto> result= userService.searchUser(keywords);
			return new ResponseEntity<List<UserRequestDto>>(result, HttpStatus.OK);
		
		}
	    
		/** Full Serach **/
		@GetMapping("/search/full/{keywords}")
		public  ResponseEntity<UserResponseDto> searchUserByFull(
				 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
		            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
		            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
		            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
				@PathVariable("keywords") String keywords){
			UserResponseDto result= userService.searchUserFull(pageNo, pageSize, sortBy, sortBy, keywords);
					
			return new ResponseEntity<UserResponseDto>(result, HttpStatus.OK);
		
		}
}
