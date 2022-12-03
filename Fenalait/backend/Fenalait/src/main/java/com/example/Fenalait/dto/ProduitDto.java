package com.example.Fenalait.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.Fenalait.model.Vente;
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
public class ProduitDto {

	private Long id;
	
	@NotBlank(message = "Veuillez entrer le nom du produit !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String nomPrdt;
	
	private Double qte;
	
	@Past(message = "La date ne peut être inférieur à la date courante !!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date date;
	
	 @NotBlank(message = "Veuillez entrer le code du produit")
	 @Size(min = 2, max = 128, message = "La taille doit être comprise entre 2 - 128 ") 
	private String code;
	
	private java.sql.Date dateExp;
	
	private Long magasinId;
    
	private Long categoryId;
	
	private Long userId;

    private float price;
    
    private String categoryNom;
	private String magasinNom;
	private String email;
	
	private List<Vente> ventes;
}
