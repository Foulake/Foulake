package com.example.Fenalait.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	private Long id;
	
	@Column(name="nomPrdt")
	private String nomPrdt;
	
	@Column(name="qte")
	private double qte;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="dateExp")
	private java.sql.Date dateExp;
	
	@ManyToOne
	private Category category;

	@ManyToOne
	private Magasin magasin;
<<<<<<< HEAD
	 
	@ManyToOne
	private User user;
=======
	@ManyToOne
	private User user;
	
	
	
>>>>>>> e41d397de22866780626fac3c1d70d850c0a4e8f
	
	//	@JsonInclude(JsonInclude.Include.NON_NULL)
//	@Transient
	@OneToMany(mappedBy ="produit")
	private List<Vente> ventes;

	public Produit() {
		super();
		
	}
	
	public Produit(Long id, String nomPrdt, double qte, Date date,
			java.sql.Date dateExp) {
		super();
		this.id = id;
		this.nomPrdt = nomPrdt;
		this.qte = qte;
		this.date = date;
		this.dateExp = dateExp;
	}
}
