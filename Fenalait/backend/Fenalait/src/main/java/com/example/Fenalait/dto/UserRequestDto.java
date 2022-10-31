package com.example.Fenalait.dto;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class UserRequestDto {
	
	private Long id;
	@NotBlank(message = "Veuillez entre votre prénom")
	private String prenom;
	
	@NotBlank(message = "Veuillez entre votre nom")
	private String nom;
	
	@NotBlank(message = "Veuillez entre votre E-mail")
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-z0-9.-]+$", message = "Entre un E-mail valide")
	private String email;
	
	@NotBlank(message = "Veuillez entre votre mot de passe")
	@Pattern(regexp = "\\S+", message = "Les espaces ne sont pas autorisés")
	@NotBlank(message = "Veuillez entrer votre mot de passe")
	private String password;  
	
	private List<String> roleName;
	  
}
