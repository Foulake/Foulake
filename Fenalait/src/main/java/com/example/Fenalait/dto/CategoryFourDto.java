package com.example.Fenalait.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.Fenalait.model.Fournisseur;

import lombok.Data;


@Data
public class CategoryFourDto {

	private Long id;
	
	@NotBlank(message = "Veuillez entrer la description de la categorie Fournisseur  !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String description;
	
	private List<Fournisseur> fournisseurs;
}
