package com.example.Fenalait.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.Fenalait.model.User;

public class Mapper {
	public static UserResponseDto userToUserResponseDto(User user) {
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setId(user.getId());
		userResponseDto.setNom(user.getNom());
		userResponseDto.setPrenom(user.getPrenom());
		userResponseDto.setEmail(user.getEmail());
//		List<String> names = new ArrayList<>();
//		List<Product> products = user.getProducts();
//		for(Product product: products) {
//			names.add(product.getName());
//		}
//		userResponseDto.setProductNames(names);
		return userResponseDto;
	}
	public static List<UserResponseDto> userToUserResponseDtos(List<User> users){
		List<UserResponseDto> userResponseDtos = new ArrayList<>();
		for(User user: users) {
			userResponseDtos.add(userToUserResponseDto(user));
		}
		return userResponseDtos;
	}
	
	

}
