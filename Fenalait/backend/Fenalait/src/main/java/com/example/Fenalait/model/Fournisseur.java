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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@ToString
@Table(name="Fournisseurs")
public class Fournisseur extends  BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Veuillez entrer le nom du Fournisseur !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String nom;
	
	@NotBlank(message = "Veuillez entrer le prénom du Fournisseur  !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String prenom;
	
	@Column(name="date")
	@Past(message = "La date de naissance ne peut être inférieur à la date courante !!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateFour;
	
	@NotBlank(message = "Le numéro ne peut pas être nul !")
	@Pattern(regexp = "^(\\+\\d{1,3}( )?)?(\\d{2}[ ]?)(\\d{2}[ ]?){2}\\d{2}$" , message = "Voici le format : +223 65 20 14 12")	
	private String tel;
	
	@ManyToOne
	private CategorieFournisseur categorieFournisseur;
	
	@OneToMany(mappedBy = "fournisseur")
	private List<Paiement> paiements;
	
	@OneToMany(mappedBy = "fournisseur")
	private List<Approvissionnement> approvissionnements;

	public void addApprovissionnement(Approvissionnement approvissionnement) {
		approvissionnements.add(approvissionnement);
	}
	
	public void removeApprovissionnement(Approvissionnement approvissionnement) {
		approvissionnements.remove(approvissionnement);
	}
	

}
