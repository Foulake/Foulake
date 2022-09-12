package com.example.Fenalait.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@ToString
@Table(name="Produits")
public class Produit extends  BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPrdt;
	
	@Column(name="nomPrdt")
	private String nomPrdt;
	
	@Column(name="qte")
	private double qte;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="dateExp")
	private java.sql.Date dateExp;
	

	//	@JsonInclude(JsonInclude.Include.NON_NULL)
//	@Transient
	@OneToMany(mappedBy ="produit")
	private List<Vente> ventes;

	public Produit() {
		super();
		
	}
	
	public Produit(Long idPrdt, String nomPrdt, double qte, Date date,
			java.sql.Date dateExp) {
		super();
		this.idPrdt = idPrdt;
		this.nomPrdt = nomPrdt;
		this.qte = qte;
		this.date = date;
		this.dateExp = dateExp;
	}
}
