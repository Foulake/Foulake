package com.example.Fenalait.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.Fenalait.model.Vente;

import lombok.Data;

@Data
public class ProduitDto {

	private Long id;
	
	@NotBlank(message = "Veuillez entrer le nom du produit !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String nomPrdt;
	
	@NotBlank(message = "Veuillez entrer la quantité du produit !!")
	private double qte;
	
	@Past(message = "La date ne peut être inférieur à la date courante !!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date date;
	
	
	private java.sql.Date dateExp;
	
	private List<Vente> ventes;
}
