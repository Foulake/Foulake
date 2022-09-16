package com.example.Fenalait.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class EmployeDto {

	private Long id;

	@Column(name="firstName")
	@NotBlank(message = "Veuillez entrer le prénom de la categorie !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String firstName;

	@Column(name="lastName")
	@NotBlank(message = "Veuillez entrer le nom de la categorie !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String lastName;

	@Column(name="email",nullable = false, unique = true)
	@NotBlank(message = "Veuillez entre votre E-mail")
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-z0-9.-]+$", message = "Entre un E-mail valide")
	private String email;
	
	private String telEmploye;
}
