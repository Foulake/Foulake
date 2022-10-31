package com.example.Fenalait.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.UserRequestDto;
import com.example.Fenalait.dto.UserResponseDto;
import com.example.Fenalait.model.Role;
import com.example.Fenalait.model.User;


@Service
public interface UserService {
	
	public User getUserProfil(String Email);
	public User getUser(Long userId);
	public User addRoleToUser(String email, String roleName);
	public Role saveRole(Role role);
	UserResponseDto getUserById(Long userId);
	public UserResponseDto getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);
	/** Search **/
	List<UserRequestDto> searchUser(String keyword);
	public UserResponseDto searchUserFull(int pageNo, int pageSize, String sortBy, String sortDir, String keyword);
	}
