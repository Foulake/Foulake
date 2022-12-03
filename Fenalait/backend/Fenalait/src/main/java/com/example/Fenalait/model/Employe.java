package com.example.Fenalait.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@ToString
@Table(name="Employes")
public class Employe extends  BaseEntity {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name="firstName")
@NotBlank(message = "Veuillez entrer le prénom de l'employé !!")
@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
private String firstName;

@Column(name="lastName")
@NotBlank(message = "Veuillez entrer le nom de l'employé !!")
@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
private String lastName;

@Column(name="titre")
@NotBlank(message = "Veuillez entrer le nom de l'employé !!")
@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
private String titre;

@Column(name="telEmploye")
private String telEmploye;

public Employe() {}

@OneToMany(mappedBy = "employe")
private List<PaiementEmploye> paiementEmployes;


public Employe(Long id, String firstName, String lastName, String titre) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.titre = titre;
}
}
