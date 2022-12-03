package com.example.Fenalait.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class EmployeDto {

	private Long id;
	
	@NotBlank(message = "Veuillez entrer le prénom de l'employé !!")
	private String firstName;
	
	@NotBlank(message = "Veuillez entrer le nom de l'employé !!")
	private String lastName;
	
	@NotBlank(message = "Veuillez entrer le nom de l'employé !!")
	private String titre;
	
	private String telEmploye;
}
