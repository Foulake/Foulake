package com.example.Fenalait.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.Fenalait.model.Paiement;

import lombok.Data;

@Data
public class FournisseurDto {

	private long id;
	
	@NotBlank(message = "Veuillez entrer le nom du Fournisseur !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String nom;
	
	@NotBlank(message = "Veuillez entrer le prénom du Fournisseur  !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String prenom;
	
	@Column(name="dateFour")
	@Past(message = "La date de naissance ne peut être inférieur à la date courante !!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date date;
	
	@NotBlank(message = "Le numéro ne peut pas être nul !")
	@Pattern(regexp = "^(\\+\\d{1,3}( )?)?(\\d{2}[ ]?)(\\d{2}[ ]?){2}\\d{2}$" , message = "Voici le format : +223 65 20 14 12")	
	private String tel;
	
	private List<Paiement> paiements;
}
