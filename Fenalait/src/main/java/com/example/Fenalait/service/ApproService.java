package com.example.Fenalait.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.ApproDto;

@Service
public interface ApproService {
	
	ApproDto createAppro(Long fournisseurId, ApproDto approDto);

	 List<ApproDto> getApprosByFournisseurId(Long fournisseurId);

	 ApproDto getApproById(Long fournisseurId, Long approId);

	 ApproDto updateAppro(Long fournisseurId, Long approId, ApproDto approDto);

	 void deleteAppro(Long fournisseurId, Long approId);
	 
	 
	 
	 ApproDto createProduitAppro(Long produitId, ApproDto approDto);

	 List<ApproDto> getApprosByProduitId(Long produitId);

	 ApproDto getProduitApproById(Long produitId, Long approId);

	 ApproDto updateProduitAppro(Long produitId, Long approId, ApproDto approDto);

	 void deleteProduitAppro(Long produitId, Long approId);

	 
	 
	 ApproDto createPaiementAppro(Long paiementId, ApproDto approDto);

	 List<ApproDto> getApprosByPaiementId(Long paiementId);

	 ApproDto getPaiementApproById(Long paiementId, Long approId);

	 ApproDto updatePaiementAppro(Long paiementId, Long approId, ApproDto approDto);

	 void deletePaiementAppro(Long paiementId, Long approId);
}
