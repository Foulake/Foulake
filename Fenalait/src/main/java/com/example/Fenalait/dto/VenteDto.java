package com.example.Fenalait.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class VenteDto {
	
	private Long id;
	
	@NotBlank(message = "Veuillez entrer la quantité de vente !!")
	private double quantite;
	
	@NotBlank(message = "Le montant ne peut pas être vide !")
	private double montant;
	
	
	private boolean remise;
	
	@Column(name="date")
	@Past(message = "La date ne peut être inférieur à la date courante !!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date date;

}
