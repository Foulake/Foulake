package com.example.Fenalait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.ApproDto;
import com.example.Fenalait.exception.BlogAPIException;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.model.Fournisseur;
import com.example.Fenalait.model.Paiement;
import com.example.Fenalait.model.Produit;
import com.example.Fenalait.model.Approvissionnement;
import com.example.Fenalait.repository.FournisseurRepository;
import com.example.Fenalait.repository.PaiementRepository;
import com.example.Fenalait.repository.ProduitRepository;
import com.example.Fenalait.repository.ApproRepository;
import com.example.Fenalait.service.ApproService;

@Service
public class ApproServiceImpl implements ApproService{


	private ApproRepository approRepository;
	
	private FournisseurRepository fournisseurRepository;
	
	private  ProduitRepository produitRepository;
	
	private PaiementRepository paiementRepository;

	public ApproServiceImpl(ApproRepository approRepository, FournisseurRepository fournisseurRepository, ProduitRepository produitRepository,
							PaiementRepository paiementRepository) {
		super();
		this.approRepository = approRepository;
		this.fournisseurRepository = fournisseurRepository;
		this.produitRepository = produitRepository;
		this.paiementRepository = paiementRepository;
	}

	@Override
	public ApproDto createAppro(Long fournisseurId, ApproDto approDto) {
		Approvissionnement appro = mapToEntity(approDto);

        // retrieve fournisseur entity by id
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElseThrow(
                () -> new ResourceNotFoundException("Fournisseur", "id", fournisseurId));

        // set fournisseur to appro entity
        appro.setFournisseur(fournisseur);

        // appro entity to DB
        Approvissionnement newAppro =  approRepository.save(appro);

        return mapToDTO(newAppro);
	}

	@Override
	public List<ApproDto> getApprosByFournisseurId(Long fournisseurId) {
		// retrieve appros by fournisseurId
        List<Approvissionnement> appros = approRepository.findByFournisseurId(fournisseurId);

        // convert list of appro entities to list of appro dto's
        return appros.stream().map(appro -> mapToDTO(appro)).collect(Collectors.toList());
	}

	@Override
	public ApproDto getApproById(Long fournisseurId, Long approId) {
		// retrieve fournisseur entity by id
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElseThrow(
                () -> new ResourceNotFoundException("Fournisseur", "id", fournisseurId));

        // retrieve appro by id
        Approvissionnement appro = approRepository.findById(approId).orElseThrow(() ->
                new ResourceNotFoundException("Appro", "id", approId));

        if(!appro.getFournisseur().getId().equals(fournisseur.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Appro does not belong to fournisseur");
        }

        return mapToDTO(appro);
	}

	@Override
	public ApproDto updateAppro(Long fournisseurId, Long approId, ApproDto approDto) {
		// retrieve fournisseur entity by id
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElseThrow(
                () -> new ResourceNotFoundException("Fournisseur", "id", fournisseurId));

        // retrieve appro by id
        Approvissionnement appro = approRepository.findById(approId).orElseThrow(() ->
                new ResourceNotFoundException("Appro", "id", approId));

        if(!appro.getFournisseur().getId().equals(fournisseur.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Appro does not belongs to fournisseur");
        }

        appro.setDateAppro(approDto.getDateAppro());
        appro.setQteAppro(approDto.getQteAppro());
        
        Approvissionnement updatedAppro = approRepository.save(appro);
        return mapToDTO(updatedAppro);
	}

	@Override
	public void deleteAppro(Long fournisseurId, Long approId) {
		// retrieve fournisseur entity by id
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElseThrow(
                () -> new ResourceNotFoundException("Fournisseur", "id", fournisseurId));

        // retrieve appro by id
        Approvissionnement appro = approRepository.findById(approId).orElseThrow(() ->
                new ResourceNotFoundException("Appro", "id", approId));

        if(!appro.getFournisseur().getId().equals(fournisseur.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Appro does not belongs to fournisseur");
        }

        approRepository.delete(appro);
	}

	
	/** Pour Produit **/
	
	@Override
	public ApproDto createProduitAppro(Long produitId, ApproDto approDto) {
		Approvissionnement appro = mapToEntity(approDto);

        // retrieve produit entity by id
        Produit produit = produitRepository.findById(produitId).orElseThrow(
                () -> new ResourceNotFoundException("Produit", "id", produitId));

        // set produit to appro entity
        appro.setProduit(produit);

        // appro entity to DB
        Approvissionnement newAppro =  approRepository.save(appro);

        return mapToDTO(newAppro);
	}

	@Override
	public List<ApproDto> getApprosByProduitId(Long produitId) {
		// retrieve appros by produitId
        List<Approvissionnement> appros = approRepository.findByProduitId(produitId);

        // convert list of appro entities to list of appro dto's
        return appros.stream().map(appro -> mapToDTO(appro)).collect(Collectors.toList());
	}

	@Override
	public ApproDto getProduitApproById(Long produitId, Long approId) {
		// retrieve produit entity by id
        Produit produit = produitRepository.findById(produitId).orElseThrow(
                () -> new ResourceNotFoundException("Produit", "id", produitId));

        // retrieve appro by id
        Approvissionnement appro = approRepository.findById(approId).orElseThrow(() ->
                new ResourceNotFoundException("Appro", "id", approId));

        if(!appro.getProduit().getId().equals(produit.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Appro does not belong to produit");
        }

        return mapToDTO(appro);
	}

	@Override
	public ApproDto updateProduitAppro(Long produitId, Long approId, ApproDto approDto) {
		// retrieve produit entity by id
        Produit produit = produitRepository.findById(produitId).orElseThrow(
                () -> new ResourceNotFoundException("Produit", "id", produitId));

        // retrieve appro by id
        Approvissionnement appro = approRepository.findById(approId).orElseThrow(() ->
                new ResourceNotFoundException("Approvissionnement", "id", approId));

        if(!appro.getProduit().getId().equals(produit.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Appro does not belongs to produit");
        }

        appro.setDateAppro(approDto.getDateAppro());
        appro.setQteAppro(approDto.getQteAppro());
        
        Approvissionnement updatedAppro = approRepository.save(appro);
        return mapToDTO(updatedAppro);
	}

	@Override
	public void deleteProduitAppro(Long produitId, Long approId) {
		// retrieve produit entity by id
        Produit produit = produitRepository.findById(produitId).orElseThrow(
                () -> new ResourceNotFoundException("Produit", "id", produitId));

        // retrieve appro by id
        Approvissionnement appro = approRepository.findById(approId).orElseThrow(() ->
                new ResourceNotFoundException("Appro", "id", approId));

        if(!appro.getProduit().getId().equals(produit.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Appro does not belongs to produit");
        }

        approRepository.delete(appro);
	}
	
	
/** Pour Paiement **/
	
	@Override
	public ApproDto createPaiementAppro(Long paiementId, ApproDto approDto) {
		Approvissionnement appro = mapToEntity(approDto);

        // retrieve paiement entity by id
        Paiement paiement = paiementRepository.findById(paiementId).orElseThrow(
                () -> new ResourceNotFoundException("Paiement", "id", paiementId));

        // set paiement to appro entity
        appro.setPaiement(paiement);

        // appro entity to DB
        Approvissionnement newAppro =  approRepository.save(appro);

        return mapToDTO(newAppro);
	}

	@Override
	public List<ApproDto> getApprosByPaiementId(Long paiementId) {
		// retrieve appros by paiementId
        List<Approvissionnement> appros = approRepository.findByPaiementId(paiementId);

        // convert list of appro entities to list of appro dto's
        return appros.stream().map(appro -> mapToDTO(appro)).collect(Collectors.toList());
	}

	@Override
	public ApproDto getPaiementApproById(Long paiementId, Long approId) {
		// retrieve paiement entity by id
        Paiement paiement = paiementRepository.findById(paiementId).orElseThrow(
                () -> new ResourceNotFoundException("Paiement", "id", paiementId));

        // retrieve appro by id
        Approvissionnement appro = approRepository.findById(approId).orElseThrow(() ->
                new ResourceNotFoundException("Appro", "id", approId));

        if(!appro.getPaiement().getId().equals(paiement.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Appro does not belong to paiement");
        }

        return mapToDTO(appro);
	}

	@Override
	public ApproDto updatePaiementAppro(Long paiementId, Long approId, ApproDto approDto) {
		// retrieve paiement entity by id
        Paiement paiement = paiementRepository.findById(paiementId).orElseThrow(
                () -> new ResourceNotFoundException("Paiement", "id", paiementId));

        // retrieve appro by id
        Approvissionnement appro = approRepository.findById(approId).orElseThrow(() ->
                new ResourceNotFoundException("Approvissionnement", "id", approId));

        if(!appro.getPaiement().getId().equals(paiement.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Appro does not belongs to paiement");
        }

        appro.setDateAppro(approDto.getDateAppro());
        appro.setQteAppro(approDto.getQteAppro());
        
        Approvissionnement updatedAppro = approRepository.save(appro);
        return mapToDTO(updatedAppro);
	}

	@Override
	public void deletePaiementAppro(Long paiementId, Long approId) {
		// retrieve paiement entity by id
        Paiement paiement = paiementRepository.findById(paiementId).orElseThrow(
                () -> new ResourceNotFoundException("Paiement", "id", paiementId));

        // retrieve appro by id
        Approvissionnement appro = approRepository.findById(approId).orElseThrow(() ->
                new ResourceNotFoundException("Appro", "id", approId));

        if(!appro.getPaiement().getId().equals(paiement.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Appro does not belongs to paiement");
        }

        approRepository.delete(appro);
	}
	
	
	private ApproDto mapToDTO(Approvissionnement appro){
     //   ApproDto approDto = mapper.map(appro, ApproDto.class);

        ApproDto approDto = new ApproDto();
        approDto.setId(appro.getId());
        approDto.setDateAppro(appro.getDateAppro());
        approDto.setQteAppro(appro.getQteAppro());
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
    
}
