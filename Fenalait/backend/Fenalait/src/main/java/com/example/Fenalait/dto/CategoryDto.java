package com.example.Fenalait.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.Fenalait.model.Produit;

import lombok.Data;

@Data
public class CategoryDto {
	
	private Long id ;
	
	@NotBlank(message = "Veuillez entrer le nom de la categorie !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String nom;
	
	private List<Produit> produits ;

}
