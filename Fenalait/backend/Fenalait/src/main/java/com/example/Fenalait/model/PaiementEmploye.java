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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@ToString
@Table(name="PaiementEmployes")
public class PaiementEmploye extends  BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="date")
	@NotBlank(message = "Veuillez entrer la date de la paiement !!")
	private Date date;
	
	@Column(name="payee")
	private boolean payee;
	
	
	@Column(name="montant")
	private double montant;
	
	@ManyToOne
	Employe employe;

}
