package com.example.Fenalait.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="Approvissionnements")
public class Approvissionnement extends  BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long idAppro;
	
	@Column(name="qteAppro")
	@NotBlank(message = "Veuillez entrer la quantité d'approvissionnement !!")
	private int qteAppro;
	
	@Column(name="dateAppro")
	@Past(message = "La date ne peut être inférieure à la date courante !!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateAppro;
	
	@ManyToOne
	@JoinColumn(name="id_prdt")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name="id_Four")
	private Fournisseur fournisseur;
	
	
	@ManyToOne
	@JoinColumn(name="id_Paie")
	private Paiement paiement;
	
	public Approvissionnement(Long idAppro) {
		this.idAppro=idAppro;
		
	}
	
	public Approvissionnement(Long idAppro, int qteAppro, Date dateAppro) {
		super();
		this.idAppro = idAppro;
		this.qteAppro = qteAppro;
		this.dateAppro = dateAppro;
	}

	public Approvissionnement() {
		
	}
	public int getQteAppro() {
		return qteAppro;
	}

	public void setQteAppro(int qteAppro) {
		this.qteAppro = qteAppro;
	}

	

	public Date getDateAppro() {
		return dateAppro;
	}

	public void setDateAppro(Date dateAppro) {
		this.dateAppro = dateAppro;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Long getIdAppro() {
		return idAppro;
	}

	public Paiement getPaiement() {
		return paiement;
	}

	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}

}
