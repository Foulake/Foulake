package com.example.Fenalait.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.ApproDto;
import com.example.Fenalait.dto.ApproResponse;
import com.example.Fenalait.dto.Mapper;
import com.example.Fenalait.exception.ResourceNotFoundExceptions;
import com.example.Fenalait.model.Fournisseur;
import com.example.Fenalait.model.Produit;
import com.example.Fenalait.model.User;
import com.example.Fenalait.model.Approvissionnement;
import com.example.Fenalait.repository.FournisseurRepository;
import com.example.Fenalait.repository.ProduitRepository;
import com.example.Fenalait.repository.ApproRepository;
import com.example.Fenalait.service.ApproService;
import com.example.Fenalait.service.FournisseurService;
import com.example.Fenalait.service.ProduitService;
import com.example.Fenalait.service.UserService;

@Service
public class ApproServiceImpl implements ApproService{

	
	private ApproRepository approvissionnementRepository;	
	private final FournisseurService fournisseurService;
	private final UserService userService;
	private final ProduitService produitService;
	private final ProduitRepository produitRepository;
	private final FournisseurRepository fournisseurRepository;
	
	@Autowired
	public ApproServiceImpl(ApproRepository approvissionnementRepository, FournisseurService fournisseurService, UserService userService,
			ProduitService produitService, ProduitRepository produitRepository, FournisseurRepository fournisseurRepository) {
		
		this.approvissionnementRepository = approvissionnementRepository;
		this.fournisseurService = fournisseurService;
		this.userService = userService;
		this.produitService = produitService;
		this.produitRepository = produitRepository;
		this.fournisseurRepository = fournisseurRepository;
	}

	@Transactional     
	@Override
	public ApproResponse addApprovissionnement(ApproDto approDto) {
		Approvissionnement approvissionnement = new Approvissionnement();
		
		
		approvissionnement.setDateAppro(approDto.getDateAppro());
		approvissionnement.setQteAppro( approDto.getQteAppro());
	
		if(approDto.getFournisseurId() == null ) {
			throw new IllegalArgumentException("Le approvissionnement manque de Categorie !");
		}
		if(approDto.getProduitId() == null) {
			throw new IllegalArgumentException("Le approvissionnement manque de Produit !");
		}
		if(approDto.getUserId() == null) {
			throw new IllegalArgumentException("Le approvissionnement manque du nom de l'utilisateur !");
		}
		
		Fournisseur fournisseur = fournisseurService.getFournisseur(approDto.getFournisseurId());
		approvissionnement.setFournisseur(fournisseur);
		
		Produit produit = produitService.getProduit(approDto.getProduitId());
		approvissionnement.setProduit(produit);
		
		User user = userService.getUser(approDto.getUserId());;
		approvissionnement.setUser(user);
		
		approvissionnement.setQteAppro(approvissionnement.getQteAppro());
		approvissionnement.setDateAppro(approvissionnement.getDateAppro());
		
		Approvissionnement approvissionnement1 = approvissionnementRepository.save(approvissionnement);
		
		approvissionnement.getProduit().setQte(approvissionnement.getProduit().getQte() + approDto.getQteAppro());
		
		
		return Mapper.approvissionnementToApproResponse(approvissionnement1);
	}

	@Override
	public ApproResponse getApprovissionnementById(Long approId) {
		Approvissionnement approvissionnement = getApprovissionnement(approId);
		return Mapper.approvissionnementToApproResponse(approvissionnement);
	}

	@Override
	public Approvissionnement getApprovissionnement(Long approId) {
		Approvissionnement approvissionnement = approvissionnementRepository.findById(approId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("Il n'existe pas d'approvissionnement avec id : " + approId));
		return approvissionnement;
	}
	
	
	@Override
	public ApproResponse deleteApprovissionnement(Long approId) {
		Approvissionnement approvissionnement = getApprovissionnement(approId);
		approvissionnementRepository.delete(approvissionnement);
		
		return Mapper.approvissionnementToApproResponse(approvissionnement);
	}

	@Transactional
	@Override
	public ApproResponse editApprovissionnement(Long approId, ApproDto approDto) {
		Approvissionnement approvissionnementEdit = getApprovissionnement(approId);
		
		approvissionnementEdit.setDateAppro(approDto.getDateAppro());
		approvissionnementEdit.setQteAppro(approDto.getQteAppro());
		
		if(approDto.getFournisseurId() != null ) {
			Fournisseur fournisseur = fournisseurService.getFournisseur(approDto.getFournisseurId());
			approvissionnementEdit.setFournisseur(fournisseur);
			
		}
		if(approDto.getProduitId() != null ) {
			Produit produit = produitService.getProduit(approDto.getProduitId());
			approvissionnementEdit.setProduit(produit);
		}
		
		if(approDto.getUserId() != null ) {
			User user = userService.getUser(approDto.getUserId());
			approvissionnementEdit.setUser(user);
		}
		
		return Mapper.approvissionnementToApproResponse(approvissionnementEdit);
	}

	@Override
	public List<ApproResponse> getApprovissionnements() {
		List<Approvissionnement> approvissionnements = StreamSupport
				.stream(approvissionnementRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return Mapper.approvissionnementToApproResponses(approvissionnements);
	}


	@Override
	public ApproResponse getAllApprovissionnements(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Approvissionnement> approvissionnements = approvissionnementRepository.findAll(pageable);
        
        List<Approvissionnement> listOfApprovissionnements = approvissionnements.getContent();
        
        List<ApproDto> content = listOfApprovissionnements.stream().map(approvissionnement -> mapToDTO(approvissionnement)).collect(Collectors.toList());
		
        ApproResponse approResponse = new ApproResponse();
        approResponse.setContent(content);
        approResponse.setPageNo(approvissionnements.getNumber());
        approResponse.setPageSize(approvissionnements.getSize());
        approResponse.setTotalElements(approvissionnements.getTotalElements());
        approResponse.setTotalPages(approvissionnements.getTotalPages());
        approResponse.setLast(approvissionnements.isLast());

        return approResponse;
	}

	
	private ApproDto mapToDTO(Approvissionnement appro){
  
		ApproDto approDto = new ApproDto();
        approDto.setId(appro.getId());
        approDto.setDateAppro(appro.getDateAppro());
        approDto.setQteAppro(appro.getQteAppro());
        
        approDto.setFournisseurNom(appro.getFournisseur().getPrenom());
        approDto.setUserNom(appro.getUser().getPrenom());
        approDto.setProduitNom(appro.getProduit().getNomPrdt());
        return  approDto;
    }

    private Approvissionnement mapToEntity(ApproDto approDto){
        //Appro appro = mapper.map(approDto, Appro.class);
        Approvissionnement appro = new Approvissionnement();
        appro.setId(approDto.getId());
        appro.setDateAppro(approDto.getDateAppro());
        appro.setQteAppro(approDto.getQteAppro());
        return  appro;
    }

	@Override
	public ApproResponse searchApprovissionnementFull(int pageNo, int pageSize, String sortBy, String sortDir,
			String keyword) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Approvissionnement> approvissionnements = approvissionnementRepository.findAll(pageable, keyword);
        
        List<Approvissionnement> listOfApprovissionnements = approvissionnements.getContent();
        
        List<ApproDto> content = listOfApprovissionnements.stream().map(approvissionnement -> mapToDTO(approvissionnement)).collect(Collectors.toList());
		
        ApproResponse approResponse = new ApproResponse();
        approResponse.setContent(content);
        approResponse.setPageNo(approvissionnements.getNumber());
        approResponse.setPageSize(approvissionnements.getSize());
        approResponse.setTotalElements(approvissionnements.getTotalElements());
        approResponse.setTotalPages(approvissionnements.getTotalPages());
        approResponse.setLast(approvissionnements.isLast());

        return approResponse;
	}

	@Override
	public List<Approvissionnement> approvissionnementJourInterval(LocalDate createdAt, LocalDate updatedAt) {
		
		return approvissionnementRepository.approvissionnementJourInterval(createdAt, updatedAt);
	}
    
}
