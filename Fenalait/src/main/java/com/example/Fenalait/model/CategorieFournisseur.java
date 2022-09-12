package com.example.Fenalait.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@ToString
@Table(name="CategorieFournisseurs")
public class CategorieFournisseur extends  BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCatFour;
	
	private String description;
	
	@OneToMany(mappedBy = "categorieFournisseur")
	private List<Fournisseur> fournisseurs;

	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}

	public CategorieFournisseur() {
		super();
		
	}
	
	

	public CategorieFournisseur(Long idCatFour, String description, List<Fournisseur> fournisseurs) {
		super();
		this.idCatFour = idCatFour;
		this.description = description;
		this.fournisseurs = fournisseurs;
	}

	public Long getIdCatFour() {
		return idCatFour;
	}

	public void setIdCatFour(Long idCatFour) {
		this.idCatFour = idCatFour;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
