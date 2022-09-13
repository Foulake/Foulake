package com.example.Fenalait.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ApproDto {

	private Long idAppro;
	
	@NotBlank(message = "Veuillez entrer la quantité d'approvissionnement !!")
	private int qteAppro;
	
	@Past(message = "La date ne peut être inférieure à la date courante !!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateAppro;
}
