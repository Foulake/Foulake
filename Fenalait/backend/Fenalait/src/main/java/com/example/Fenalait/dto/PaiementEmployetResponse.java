package com.example.Fenalait.dto;

import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaiementEmployetResponse {

	private Long id;
	
	private Long employeId;
	
	private Date date;
	
	private boolean payee;
	
	private double montant;
	private String employeNom;
	
	
	private List<PaiementDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
