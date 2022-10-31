package com.example.Fenalait.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;

import com.example.Fenalait.model.Approvissionnement;

import lombok.Data;

@Data
public class PaiementDto {

	private Long id;
	
	@NotBlank(message = "Veuillez entrer la date de la paiement !!")
	private Date date;
	
	private boolean payee;
	
	@NotBlank(message = "Veuillez entrer la quantité à payer !!")
	private double qte;
	
	private double montant;
	
	private List<Approvissionnement> approvissionnements;
}
