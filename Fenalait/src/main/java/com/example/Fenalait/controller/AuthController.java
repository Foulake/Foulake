package com.example.Fenalait.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fenalait.exception.ResourceNotFoundExceptions;
import com.example.Fenalait.model.Role;
import com.example.Fenalait.model.User;
import com.example.Fenalait.repository.RoleRepository;
import com.example.Fenalait.repository.UserRepository;
import com.example.Fenalait.token.AuthRequest;
import com.example.Fenalait.token.AuthResponse;
import com.example.Fenalait.token.JwtTokenUtil;


@RestController
@RequestMapping("/auth")
public class AuthController {
	


    @Autowired AuthenticationManager authManager;

    @Autowired JwtTokenUtil jwtUtil;

    @Autowired UserRepository repository;
    @Autowired RoleRepository roleRepository;

    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {

        try {

            Authentication authentication = authManager.authenticate(

                    new UsernamePasswordAuthenticationToken(

                            request.getEmail(), request.getPassword())

            );

            User user = (User) authentication.getPrincipal();

            String accessToken = jwtUtil.generateAccessToken(user);
            String refreshToken = jwtUtil.generateRefreshToken(user);

            AuthResponse response = new AuthResponse(user.getEmail(), accessToken, refreshToken);

             

            return ResponseEntity.ok().body(response);

             

        } catch (BadCredentialsException ex) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }

    }
    
    
    @PostMapping("/sign")

    public ResponseEntity<?> register(@RequestBody @Valid User user) {

    	if(repository.findByEmail(user.getEmail()).isPresent()) {
    		throw new ResourceNotFoundExceptions(" Il existe bien un compte avec cet E-mail : " +user.getEmail());
    	}
    	
    	Role roleUser = roleRepository.findByName("ROLE_CUSTOMER");
    	user.addRole(roleUser);
    	
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		//user.setConfirmPassword(encoder.encode(user.getConfirmPassword()));
    	user.setEmail(user.getEmail());
    	//user.setConfirmEmail(user.getConfirmEmail());
    	user.setRoles(user.getRoles());
    	user.setNom(user.getNom());
    	user.setPrenom(user.getPrenom());

            repository.save(user);
            
            String accessToken = jwtUtil.generateAccessToken(user);
            String refreshToken = jwtUtil.generateRefreshToken(user);

            AuthResponse response = new AuthResponse(user.getEmail(), accessToken, refreshToken);

            return ResponseEntity.ok().body(response);

    }
}
