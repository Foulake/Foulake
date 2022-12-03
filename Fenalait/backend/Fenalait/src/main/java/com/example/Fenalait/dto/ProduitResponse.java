package com.example.Fenalait.dto;

import java.util.Date;
import java.util.List;

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
public class ProduitResponse {

	private Long id; 
	
	private String nomPrdt;
	private float price;
	private Double qte;
	private Date date;
	private java.sql.Date dateExp;
	
	private String name;

	private String code;
	    
    private Long magasinId;
    
	private Long categoryId;
	
	private Long userId;

    private String categoryNom;
	private String magasinNom;
	private String email;
	
	
	private List<ProduitDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
