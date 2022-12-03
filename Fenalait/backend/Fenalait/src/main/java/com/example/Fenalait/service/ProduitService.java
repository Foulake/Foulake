package com.example.Fenalait.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.ProduitDto;
import com.example.Fenalait.dto.ProduitResponse;
import com.example.Fenalait.model.Produit;


@Service
public interface ProduitService {

	public ProduitResponse addProduit(ProduitDto produitDto );
	public ProduitResponse getProduitById(Long productId);
	public Produit getProduit(Long productId);
	public List<ProduitResponse> getProduits();
	public ProduitResponse getAllProduits(int pageNo, int pageSize, String sortBy, String sortDir);
	public ProduitResponse deleteProduit(Long productId);
	public ProduitResponse editProduit(Long productId, ProduitDto produitDto);
	
	public ProduitResponse addCategoryToProduit(Long productId, Long categoryId);
	public ProduitResponse addMagasinToProduit(Long productId, Long magasinId);
	
	public ProduitResponse removeCategoryFromProduit(Long productId, Long categoryId);
	public ProduitResponse removeMagasinFromProduit(Long productId, Long magasinId);
	public ProduitResponse addUserToProduit(Long productId, Long userId);
	public ProduitResponse removeUserFromProduit(Long productId, Long userId);
	
	public List<ProduitDto> searchProduit(String keyword);
	
	public ProduitResponse searchProduitFull(int pageNo, int pageSize, String sortBy, String sortDir, String keyword);
}
