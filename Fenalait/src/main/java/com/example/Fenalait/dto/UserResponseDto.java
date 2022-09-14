package com.example.Fenalait.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_DEFAULT)
public class UserResponseDto {
	
	private Long id;
	private String nom;
	private String prenom;
	private String email;
	private String nomProduit;
	//private List<String> ProductNames;
	
	private List<UserRequestDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
	
	public UserResponseDto(Long id, String nom, String email, String name) {
		this.id = id;
		this.nom = nom;
		this.nomProduit = name;
		this.email = email;
	}
		
}
