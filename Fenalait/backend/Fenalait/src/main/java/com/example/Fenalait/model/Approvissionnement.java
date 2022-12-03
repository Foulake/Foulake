package com.example.Fenalait.model;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
	private Long id;
	
	@Column(name="qteAppro")
	//@NotBlank(message = "Veuillez entrer la quantité d'approvissionnement !!")
	private Double qteAppro;
	
	@Column(name="dateAppro")
	@Past(message = "La date ne peut être inférieure à la date courante !!")
    //@LocalDateFormat(iso = LocalDateFormat.ISO.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonSerialize(using = LocalDateSerializer.class)
//	@JsonDeserialize(using = LocalDateDeserializer.class)
	
	private LocalDate dateAppro;
	
	@ManyToOne
	@JoinColumn(name="id_prdt")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name="id_Four")
	private Fournisseur fournisseur;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "approvissionnement")
	private List<Paiement>  paiements;
	
	public Approvissionnement(Long id) {
		this.id=id;
		
	}
	
	public Approvissionnement(Long id, Double qteAppro, LocalDate dateAppro) {
		super();
		this.id = id;
		this.qteAppro = qteAppro;
		this.dateAppro = dateAppro;
	}

	public Approvissionnement() {
		
	}
	public Double getQteAppro() {
		return qteAppro;
	}

	public void setQteAppro(Double qteAppro) {
		this.qteAppro = qteAppro;
	}

	

	public LocalDate getLocalDateAppro() {
		return dateAppro;
	}

	public void setLocalDateAppro(LocalDate dateAppro) {
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
		return id;
	}

	

}
