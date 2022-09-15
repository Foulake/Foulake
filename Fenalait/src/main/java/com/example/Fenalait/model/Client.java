package com.example.Fenalait.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@ToString
@Table(name="Clients")
public class Client extends  BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nomClient")
	@NotBlank(message = "Veuillez entrer le nom du client !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String nomClient;
	
	@Column(name="prenomClient")
	@NotBlank(message = "Veuillez entrer le prénom du client !!")
	@Size(min = 2, max = 125,  message = "La taille doit être comprise entre 2-125 ")
	private String prenomClient;
	
	@Column(unique = true, nullable = false, length = 12, name="telClient" )
	@NotBlank(message = "Le numéro ne peut pas être nul !")
	@Pattern(regexp = "^(\\+\\d{1,3}( )?)?(\\d{2}[ ]?)(\\d{2}[ ]?){2}\\d{2}$" , message = "Voici le format : +223 65 20 14 12")	
	private String telClient;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL) 
	private List<Vente> ventes;
	
	public List<Vente> getVentes() {
		return ventes;
	}

	public void setVentes(List<Vente> ventes) {
		this.ventes = ventes;
	}

	public Client() {
		super();
		
	}
	
	
	public Client(Long id, String nomClient, String prenomClient, String telClient) {
		super();
		this.id = id;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.telClient = telClient;
	}
}
