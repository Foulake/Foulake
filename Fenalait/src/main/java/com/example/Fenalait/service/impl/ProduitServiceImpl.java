package com.example.Fenalait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.ProduitDto;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.exception.BlogAPIException;
import com.example.Fenalait.model.Category;
import com.example.Fenalait.model.Produit;
import com.example.Fenalait.repository.CategoryRepository;
import com.example.Fenalait.repository.ProduitRepository;
import com.example.Fenalait.service.ProduitService;

@Service
public class ProduitServiceImpl implements ProduitService{

	private ProduitRepository produitRepository;
	
	private CategoryRepository categoryRepository;
	
	

	public ProduitServiceImpl(ProduitRepository produitRepository, CategoryRepository categoryRepository) {
		super();
		this.produitRepository = produitRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public ProduitDto createProduit(Long categoryId, ProduitDto produitDto) {
		Produit produit = mapToEntity(produitDto);

        // retrieve category entity by id
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", categoryId));

        // set category to produit entity
        produit.setCategory(category);

        // produit entity to DB
        Produit newProduit =  produitRepository.save(produit);

        return mapToDTO(newProduit);
	}

	@Override
	public List<ProduitDto> getProduitsByCategoryId(Long categoryId) {
		// retrieve produits by categoryId
        List<Produit> produits = produitRepository.findByCategoryId(categoryId);

        // convert list of produit entities to list of produit dto's
        return produits.stream().map(produit -> mapToDTO(produit)).collect(Collectors.toList());
	}

	@Override
	public ProduitDto getProduitById(Long categoryId, Long produitId) {
		// retrieve category entity by id
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", categoryId));

        // retrieve produit by id
        Produit produit = produitRepository.findById(produitId).orElseThrow(() ->
                new ResourceNotFoundException("Produit", "id", produitId));

        if(!produit.getCategory().getId().equals(category.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Produit does not belong to category");
        }

        return mapToDTO(produit);
	}

	@Override
	public ProduitDto updateProduit(Long categoryId, Long produitId, ProduitDto produitDto) {
		// retrieve category entity by id
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", categoryId));

        // retrieve produit by id
        Produit produit = produitRepository.findById(produitId).orElseThrow(() ->
                new ResourceNotFoundException("Produit", "id", produitId));

        if(!produit.getCategory().getId().equals(category.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Produit does not belongs to category");
        }

        produit.setNomPrdt(produitDto.getNomPrdt());
        produit.setQte(produitDto.getQte());
        produit.setDateExp(produitDto.getDateExp());
        produit.setDate(produitDto.getDate());
        
        Produit updatedProduit = produitRepository.save(produit);
        return mapToDTO(updatedProduit);
	}

	@Override
	public void deleteProduit(Long categoryId, Long produitId) {
		// retrieve category entity by id
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", categoryId));

        // retrieve produit by id
        Produit produit = produitRepository.findById(produitId).orElseThrow(() ->
                new ResourceNotFoundException("Produit", "id", produitId));

        if(!produit.getCategory().getId().equals(category.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Produit does not belongs to category");
        }

        produitRepository.delete(produit);
	}

	
	private ProduitDto mapToDTO(Produit produit){
     //   ProduitDto produitDto = mapper.map(produit, ProduitDto.class);

        ProduitDto produitDto = new ProduitDto();
        produitDto.setId(produit.getId());
        produitDto.setNomPrdt(produit.getNomPrdt());
        produitDto.setDateExp(produit.getDateExp());
        produitDto.setQte(produit.getQte());
        produitDto.setDate(produit.getDate());
        return  produitDto;
    }

    private Produit mapToEntity(ProduitDto produitDto){
        //Produit produit = mapper.map(produitDto, Produit.class);
        Produit produit = new Produit();
        produit.setId(produitDto.getId());
        produit.setNomPrdt(produitDto.getNomPrdt());
        produit.setQte(produitDto.getQte());
        produit.setDateExp(produitDto.getDateExp());
        return  produit;
    }
}
