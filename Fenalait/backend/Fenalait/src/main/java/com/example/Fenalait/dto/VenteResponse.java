package com.example.Fenalait.dto;

import java.util.Date;
import java.util.List;

import com.example.Fenalait.model.Vente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_DEFAULT)
public class VenteResponse {
	
	private Long id;
	private double montant;
	private double quantite;
	private boolean remise;
	private Date date;
	    
    private Long produitId;
    
	private Long clientId;
	
	private Long userId;

    private String clientNom;
	private String produitNom;
	private String userNom;
	
	private List<VenteDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
