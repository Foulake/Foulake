package com.example.Fenalait.service.impl;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.Mapper;
import com.example.Fenalait.dto.ProduitDto;
import com.example.Fenalait.dto.ProduitResponse;
import com.example.Fenalait.exception.ResourceNotFoundExceptions;
import com.example.Fenalait.exception.ResourceAlredyExistException;
import com.example.Fenalait.model.Category;
import com.example.Fenalait.model.Produit;
import com.example.Fenalait.model.User;
import com.example.Fenalait.model.Magasin;
import com.example.Fenalait.repository.CategoryRepository;
import com.example.Fenalait.repository.MagasinRepository;
import com.example.Fenalait.repository.ProduitRepository;
import com.example.Fenalait.service.CategoryService;
import com.example.Fenalait.service.MagasinService;
import com.example.Fenalait.service.ProduitService;
import com.example.Fenalait.service.UserService;


@Service
public class ProduitServiceImpl implements ProduitService{

	private ProduitRepository produitRepository;
	
	private final CategoryService categoryService;
	private final UserService userService;
	private final MagasinService magasinService;
	private final MagasinRepository magasinRepository;
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public ProduitServiceImpl(ProduitRepository produitRepository, CategoryService categoryService,UserService userService,
			MagasinService magasinService, MagasinRepository magasinRepository, CategoryRepository categoryRepository) {
		
		this.produitRepository = produitRepository;
		this.categoryService = categoryService;
		this.userService = userService;
		this.magasinService = magasinService;
		this.magasinRepository = magasinRepository;
		this.categoryRepository = categoryRepository;
	}

	@Transactional     
	@Override
	public ProduitResponse addProduit(ProduitDto produitDto) {
		Produit produit = new Produit();
		if(produitRepository.existsProduitByCode(produitDto.getCode())) {
			throw new ResourceAlredyExistException(" Il existe bien un produit avec cet code : " +produitDto.getCode());
		}else {
		produit.setNomPrdt(produitDto.getNomPrdt());
		produit.setPrice(produitDto.getPrice());
		produit.setCode(produitDto.getCode());
		produit.setDate(produitDto.getDate());
		produit.setDateExp(produitDto.getDateExp());
		produit.setQte(produitDto.getQte());
		}
		if(produitDto.getCategoryId() == null ) {
			throw new IllegalArgumentException("Le produit manque de Categorie !");
		}
		if(produitDto.getMagasinId() == null) {
			throw new IllegalArgumentException("Le produit manque de Magasin !");
		}
		if(produitDto.getUserId() == null) {
			throw new IllegalArgumentException("Le produit manque du nom de l'utilisateur !");
		}
		
		Category category = categoryService.getCategory(produitDto.getCategoryId());
		produit.setCategory(category);
		
		Magasin magasin = magasinService.getMagasin(produitDto.getCategoryId());
		produit.setMagasin(magasin);
		
		User user = userService.getUser(produitDto.getUserId());;
		produit.setUser(user);
		
		produit.setNomPrdt(produit.getNomPrdt());
		produit.setPrice(produit.getPrice());
		produit.setCode(produit.getCode());
		produit.setDate(produit.getDate());
		produit.setDateExp(produit.getDateExp());
		produit.setQte(produit.getQte());
		
		Produit produit1 = produitRepository.save(produit);
		return Mapper.produitToProduitResponse(produit1);
	}

	@Override
	public ProduitResponse getProduitById(Long produitId) {
		Produit produit = getProduit(produitId);
		return Mapper.produitToProduitResponse(produit);
	}

	@Override
	public Produit getProduit(Long produitId) {
		Produit produit = produitRepository.findById(produitId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("Il n'existe pas de produit avec id : " + produitId));
		return produit;
	}
	
	
	@Override
	public ProduitResponse deleteProduit(Long produitId) {
		Produit produit = getProduit(produitId);
		produitRepository.delete(produit);
		return Mapper.produitToProduitResponse(produit);
	}

	@Transactional
	@Override
	public ProduitResponse editProduit(Long produitId, ProduitDto produitDto) {
		Produit produitEdit = getProduit(produitId);
		
		produitEdit.setNomPrdt(produitDto.getNomPrdt());
		produitEdit.setPrice(produitDto.getPrice());
		produitEdit.setCode(produitDto.getCode());
		produitEdit.setDate(produitDto.getDate());
		produitEdit.setDateExp(produitDto.getDateExp());
		produitEdit.setQte(produitDto.getQte());
		
		if(produitDto.getCategoryId() != null ) {
			Category category = categoryService.getCategory(produitDto.getCategoryId());
			produitEdit.setCategory(category);
			
		}
		if(produitDto.getMagasinId() != null ) {
			Magasin magasin = magasinService.getMagasin(produitDto.getMagasinId());
			produitEdit.setMagasin(magasin);
		}
		
		if(produitDto.getUserId() != null ) {
			User user = userService.getUser(produitDto.getUserId());
			produitEdit.setUser(user);
		}
		
		return Mapper.produitToProduitResponse(produitEdit);
	}

	@Override
	public ProduitResponse addCategoryToProduit(Long produitId, Long categoryId) {
		Produit produit = getProduit(produitId);
		Category category = categoryService.getCategory(categoryId);
		if(Objects.nonNull(produit.getCategory())) {
			throw new IllegalArgumentException("Il exist déjà un produit avec cette categorie");
		}
		produit.setCategory(category);
		category.addProduit(produit);
		return Mapper.produitToProduitResponse(produit);
	}

	@Override
	public ProduitResponse addMagasinToProduit(Long produitId, Long magasinId) {
		Produit produit = getProduit(produitId);
		Magasin magasin = magasinService.getMagasin(magasinId);		
		if(Objects.nonNull(produit.getMagasin())) {
			throw new IllegalArgumentException("Il exist déjà un produit avec cet Magasin");
		}
		produit.setMagasin(magasin);
		magasin.addProduit(produit);
		return Mapper.produitToProduitResponse(produit);
	}

	@Override
	public ProduitResponse removeCategoryFromProduit(Long produitId, Long categoryId) {
		Produit produit = getProduit(produitId);
		Category category = categoryService.getCategory(categoryId);
		if(!(Objects.nonNull(produit.getCategory()))) {
			throw new ResourceNotFoundExceptions("Il exist pas un produit avec cette categorie ID " +categoryService.getCategory(categoryId));
			//IllegalArgumentException("Il exist pas un produit avec cette categorie ID " +categoryService.getCategory(categoryId));
		}
		produit.setCategory(null);
		category.removeProduit(produit);
		return Mapper.produitToProduitResponse(produit);
	}

	@Override
	public ProduitResponse removeMagasinFromProduit(Long produitId, Long magasinId) {
		Produit produit = getProduit(produitId);
		Magasin magasin = magasinService.getMagasin(magasinId);
		if(!(Objects.nonNull(produit.getMagasin()))) {
			throw new IllegalArgumentException("Il exist pas un produit dans cet magasin avec cet ID :" + magasinService.getMagasin(magasinId));
		}
		produit.setMagasin(null);
		magasin.removeProduit(produit);
		return Mapper.produitToProduitResponse(produit);
	}


	@Override
	public List<ProduitResponse> getProduits() {
		List<Produit> produits = StreamSupport
				.stream(produitRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return Mapper.produitToProduitResponses(produits);
	}

	@Override
	public ProduitResponse addUserToProduit(Long produitId, Long userId) {
		
		Produit produit = getProduit(produitId);
		User user = userService.getUser(userId);		
		if(Objects.nonNull(produit.getUser())) {
			throw new IllegalArgumentException("Il exist déjà un produit avec cet user");
		}
		produit.setUser(user);
		user.addProduit(produit);
		return Mapper.produitToProduitResponse(produit);
	}

	@Override
	public ProduitResponse removeUserFromProduit(Long produitId, Long userId) {
		
		Produit produit = getProduit(produitId);
		User user = userService.getUser(userId);
		if(!(Objects.nonNull(produit.getUser()))) {
			throw new IllegalArgumentException("Il exist pas un produit avec cet user ID " +userService.getUser(userId));
		}
		produit.setUser(null);
		user.removeProduit(produit);
		return Mapper.produitToProduitResponse(produit);
	}

	@Override
	public ProduitResponse getAllProduits(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Produit> produits = produitRepository.findAll(pageable);
        
        List<Produit> listOfProduits = produits.getContent();
        
        List<ProduitDto> content = listOfProduits.stream().map(produit -> mapToDTO(produit)).collect(Collectors.toList());
		
        ProduitResponse produitResponse = new ProduitResponse();
        produitResponse.setContent(content);
        produitResponse.setPageNo(produits.getNumber());
        produitResponse.setPageSize(produits.getSize());
        produitResponse.setTotalElements(produits.getTotalElements());
        produitResponse.setTotalPages(produits.getTotalPages());
        produitResponse.setLast(produits.isLast());

        return produitResponse;
	}

	 // convert Entity into DTO
    private ProduitDto mapToDTO(Produit produit){
      //  ProduitDto produitDto = mapper.map(produit, ProduitDto.class);
        ProduitDto produitDto = new ProduitDto();
        
        produitDto.setId(produit.getId());
        produitDto.setNomPrdt(produit.getNomPrdt());
        produitDto.setCode(produit.getCode());
        produitDto.setPrice(produit.getPrice());
        produitDto.setQte(produit.getQte());
        produitDto.setDateExp(produit.getDateExp());
        produitDto.setDate(produit.getDate());
        produitDto.setMagasinNom(produit.getMagasin().getNomMagasin());
        produitDto.setCategoryNom(produit.getCategory().getNom());
       // produitDto.setMagasinId(produit.getMagasin().getId());
        produitDto.setEmail(produit.getUser().getEmail());
        
//        produitDto.setDescription(produit.getDescription());
//        produitDto.setContent(produit.getContent());
        return produitDto;
    }

    /** Search **/
	@Override
	public List<ProduitDto> searchProduit(String keyword) {
		List<Produit> produits = this.produitRepository.findByNomPrdt(keyword);
		//List<Produit> produits = this.produitRepository.findAll(keyword);
		List<ProduitDto> produitDtos=produits.stream().map(produit -> mapToDTO(produit)).collect(Collectors.toList());
		return produitDtos;
		
		
	}

	@Override
	public ProduitResponse searchProduitFull(int pageNo, int pageSize, String sortBy, String sortDir, String keyword) {
		//List<Produit> produits = this.produitRepository.findByName(keyword);
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Produit> produits = produitRepository.findAll(pageable, keyword);
        
        List<Produit> listOfProduits = produits.getContent();
        
        List<ProduitDto> content = listOfProduits.stream().map(produit -> mapToDTO(produit)).collect(Collectors.toList());
		
        ProduitResponse produitResponse = new ProduitResponse();
        produitResponse.setContent(content);
        produitResponse.setPageNo(produits.getNumber());
        produitResponse.setPageSize(produits.getSize());
        produitResponse.setTotalElements(produits.getTotalElements());
        produitResponse.setTotalPages(produits.getTotalPages());
        produitResponse.setLast(produits.isLast());

        return produitResponse;
	}
}
