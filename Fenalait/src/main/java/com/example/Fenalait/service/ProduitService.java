package com.example.Fenalait.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.ProduitDto;

@Service
public interface ProduitService {

	 ProduitDto createProduit(Long categoryId, ProduitDto produitDto);

	 List<ProduitDto> getProduitsByCategoryId(Long categoryId);

	 ProduitDto getProduitById(Long categoryId, Long produitId);

	 ProduitDto updateProduit(Long categoryId, Long produitId, ProduitDto produitDto);

	 void deleteProduit(Long categoryId, Long produitId);
	 
	 
	 ProduitDto createMagasinProduit(Long magasinId, ProduitDto produitDto);

	 List<ProduitDto> getProduitsByMagasinId(Long magasinId);

	 ProduitDto getMagasinProduitById(Long magasinId, Long produitId);

	 ProduitDto updateMagasinProduit(Long magasinId, Long produitId, ProduitDto produitDto);

	 void deleteMagasinProduit(Long magasinId, Long produitId);
}
