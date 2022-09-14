package com.example.Fenalait.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.Mapper;
import com.example.Fenalait.dto.UserRequestDto;
import com.example.Fenalait.dto.UserResponseDto;
import com.example.Fenalait.model.Role;
import com.example.Fenalait.model.User;
import com.example.Fenalait.repository.RoleRepository;
import com.example.Fenalait.repository.UserRepository;
import com.example.Fenalait.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
private final UserRepository userRepository;
	
private final RoleRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository repository) {
		this.userRepository = userRepository;
		this.repository = repository;
	}

	public User getUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(
		() -> new IllegalArgumentException("Il n'existe pas un user avec id : " + userId));	
	}

	@Override
	public UserResponseDto getUserById(Long userId) {
		User user = getUser(userId);
		return Mapper.userToUserResponseDto(user);
	}

	@Override
	public User addRoleToUser(String email, String roleName) {
		User user = userRepository.findByEmail(email).get();
		Role role = repository.findByName(roleName);
		user.getRoles().add(role);
		return user;
	}

	@Override
	public Role saveRole(Role role) {
		return repository.save(role);
	}

	@Override
	public UserResponseDto getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<User> users = userRepository.findAll(pageable);

        // get content for page object
        List<User> listOfUsers = users.getContent();

        List<UserRequestDto> content= listOfUsers.stream().map(user -> mapToDTO(user)).collect(Collectors.toList());

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setContent(content);
        userResponseDto.setPageNo(users.getNumber());
        userResponseDto.setPageSize(users.getSize());
        userResponseDto.setTotalElements(users.getTotalElements());
        userResponseDto.setTotalPages(users.getTotalPages());
        userResponseDto.setLast(users.isLast());

        return userResponseDto;
	}

	// convert Entity into DTO
    private UserRequestDto mapToDTO(User user){
      //  UserRequestDto userRequestDto = mapper.map(user, UserRequestDto.class);
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setId(user.getId());
        userRequestDto.setNom(user.getNom());
        userRequestDto.setEmail(user.getEmail());
        userRequestDto.setPrenom(user.getPrenom());
        List<String> names = new ArrayList<>();
    	Set<Role> roles = user.getRoles();
    	for(Role role: roles) {
    		names.add(role.getName());
    	}
    	userRequestDto.setRoleName(names);
        
        return userRequestDto;
    }

    /** Search **/
	@Override
	public List<UserRequestDto> searchUser(String keyword) {
		List<User> users = this.userRepository.findAll(keyword);
		List<UserRequestDto> userRequestDtos=users.stream().map(user -> mapToDTO(user)).collect(Collectors.toList());
		return userRequestDtos;
		
	}

	@Override
	public User getUserProfil(String Email) {
		return userRepository.findByEmail(Email).get();
	}

	@Override
	public UserResponseDto searchUserFull(int pageNo, int pageSize, String sortBy, String sortDir, String keyword) {
		//List<User> users = this.userRepository.findByName(keyword);
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<User> users = userRepository.findAll(pageable, keyword);
        
        List<User> listOfUsers = users.getContent();
        
        List<UserRequestDto> content = listOfUsers.stream().map(user -> mapToDTO(user)).collect(Collectors.toList());
		
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setContent(content);
        userResponseDto.setPageNo(users.getNumber());
        userResponseDto.setPageSize(users.getSize());
        userResponseDto.setTotalElements(users.getTotalElements());
        userResponseDto.setTotalPages(users.getTotalPages());
        userResponseDto.setLast(users.isLast());
        
		return userResponseDto;

        
}
	
	
}
