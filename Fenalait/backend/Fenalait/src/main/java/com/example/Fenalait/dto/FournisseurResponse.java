package com.example.Fenalait.dto;

import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurResponse {

	private Long id; 
	private String nom;
	
	private String prenom;
	
	private Date dateFour;
	
	private String tel;
	
	private String categoryFourNom;
	private Long categoryFourId;
	
	private List<FournisseurDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    private Double qteAppro;

	public FournisseurResponse(String nom, String prenom, String tel, Double qteAppro) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.qteAppro = qteAppro;
	}
    
    
}
