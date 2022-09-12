package com.example.Fenalait.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@ToString
@Table(name="Employe")
public class Employe extends  BaseEntity {
@Id	
@GeneratedValue
private long id;

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

@Column(name="telEmploye")
private String telEmploye;

public Employe() {
	
}

public Employe(long id, String firstName, String lastName, String email) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
}
}
