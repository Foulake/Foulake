package com.example.Fenalait.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

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
public class VenteDto {
	
	private Long id;
    private Long produitId;
    
	private Long clientId;
	
	private Long userId;
	//@NotBlank(message = "Veuillez entrer la quantité de vente !!")
	private double quantite;
	
	//@NotBlank(message = "Le montant ne peut pas être vide !")
	private double montant;
	
	
	private boolean remise;
	
	@Column(name="date")
	@Past(message = "La date ne peut être inférieur à la date courante !!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date date;

}
