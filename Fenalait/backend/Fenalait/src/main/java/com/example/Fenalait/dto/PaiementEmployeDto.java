package com.example.Fenalait.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;

import com.example.Fenalait.model.Approvissionnement;
import com.example.Fenalait.model.Employe;

import lombok.Data;

@Data
public class PaiementEmployeDto {

	private Long id;
	
	@NotBlank(message = "Veuillez entrer la date de la paiement !!")
	private Date date;
	
	private boolean payee;
	
	@NotBlank(message = "Veuillez entrer le montant à payer !!")
	private double montant;
	
	//private List<Employe> employes;
}
