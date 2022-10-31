package com.example.Fenalait.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.Fenalait.model.Magasin;
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
public class LocaliteRequestDto {
	
	private Long id ;
	
	@NotBlank(message = "Veuillez entrer le nom de la localité !!")
	@Size(min = 2, max = 75)
	private String nom;
	

	@NotBlank(message = "Veuillez entrer la description de la localitée !!")
	@Size(min = 2, max = 75)
	private String description;

	private List<Magasin> magasins;
	
	

}
