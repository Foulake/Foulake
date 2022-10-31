package com.example.Fenalait.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@ToString
@Table(name="magasins")
public class Magasin extends  BaseEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@NotBlank(message = "Veuillez entrer le nom du magasin !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String nomMagasin;
	
	@ManyToOne
	private Localite localite;
	
	@OneToMany(mappedBy = "magasin", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Produit> produits;

	public Magasin() {}
	
	public Magasin(String nomMagasin) {
		this.nomMagasin = nomMagasin;
	}
	public Magasin(String nomMagasin, List<Produit> produits) {
		super();
		this.nomMagasin = nomMagasin;
		this.produits = produits;
	}
	
	public void addProduit(Produit produit) {
		produits.add(produit);
	}
	public void removeProduit(Produit produit) {
		produits.remove(produit);
	}

}
