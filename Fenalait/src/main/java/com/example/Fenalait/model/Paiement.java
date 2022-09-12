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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@ToString
@Table(name="Paiements")
public class Paiement extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPai;
	
	@Column(name="datePai")
	@NotBlank(message = "Veuillez entrer la date de la paiement !!")
	private Date datePai;
	
	@Column(name="payee")
	private boolean payee;
	
	@Column(name="qtePaie")
	@NotBlank(message = "Veuillez entrer la quantité à payer !!")
	private double qtePaie;
	
	@Column(name="montantPai")
	private double montantPai;
	
	@ManyToOne
	private Fournisseur fournisseur;
	
	@OneToMany(mappedBy = "paiement")
	private List<Approvissionnement> approvissionnements;
	
	
	public Paiement () {
		
	}

	public Paiement(Long idPai, Date datePai,boolean payee,double qtePaie, double montantPai) {
		super();
		this.idPai = idPai;
		this.datePai = datePai;
		this.payee = payee;
		this.qtePaie = qtePaie;
		this.montantPai = montantPai;
	}
}
