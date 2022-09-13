package com.example.Fenalait.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@ToString
@Table(name="Ventes")
public class Vente extends  BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="quantite")
	@NotBlank(message = "Veuillez entrer la quantité de la vente !!")
	private double quantite;
	
	@Column(name="montant")
	@NotBlank(message = "Le montant ne peut pas être vide !")
	private double montant;
	
	@Column(name="remise")
	private boolean remise;
	
	@Column(name="date")
	@Past(message = "La date ne peut être inférieur à la date courante !!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date date;
	
	@ManyToOne
	private  Produit produit;
	
	@ManyToOne
	private Client client;
	
	public Vente() {
		super();
	}

	public Vente(Long id, double quantite, double montant, boolean remise, Date date) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.montant = montant;
		this.remise = remise;
		this.date = date;
	}

}
