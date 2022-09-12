package com.example.Fenalait.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "localites")
public class Localite extends BaseEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@NotBlank(message = "Veuillez entrer le nom de la localité !!")
	@Size(min = 2, max = 75)
	private String nom;
	
	@NotBlank(message = "Veuillez entrer la description de la localitée !!")
	@Size(min = 2, max = 75)
	private String description;
	
	
	@OneToMany(mappedBy = "localite", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Magasin> magasins;

	public Localite() {}
	
	public Localite(String nom, String description, List<Magasin> magasins) {
		super();
		this.nom = nom;
		this.description = description;
		this.magasins = magasins;
	}
	
	public void addMagasin(Magasin magasin) {
		magasins.add(magasin);
	}
	public void removeMagasin(Magasin magasin) {
		magasins.remove(magasin);
	}
	
	
}
