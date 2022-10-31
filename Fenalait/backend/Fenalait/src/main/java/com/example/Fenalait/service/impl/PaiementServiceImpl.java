package com.example.Fenalait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.PaiementDto;
import com.example.Fenalait.dto.PaiementResponse;
import com.example.Fenalait.exception.BlogAPIException;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.model.Fournisseur;
import com.example.Fenalait.model.Paiement;
import com.example.Fenalait.repository.FournisseurRepository;
import com.example.Fenalait.repository.MagasinRepository;
import com.example.Fenalait.repository.PaiementRepository;
import com.example.Fenalait.service.PaiementService;

@Service
public class PaiementServiceImpl implements PaiementService{

	private PaiementRepository paiementRepository;
	
	private FournisseurRepository fournisseurRepository;
	

	public PaiementServiceImpl(PaiementRepository paiementRepository, FournisseurRepository fournisseurRepository, MagasinRepository magasinRepository) {
		super();
		this.paiementRepository = paiementRepository;
		this.fournisseurRepository = fournisseurRepository;
	}

	@Override
	public PaiementDto createPaiement(Long fournisseurId, PaiementDto paiementDto) {
		Paiement paiement = mapToEntity(paiementDto);

        // retrieve fournisseur entity by id
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElseThrow(
                () -> new ResourceNotFoundException("Fournisseur", "id", fournisseurId));

        // set fournisseur to paiement entity
        paiement.setFournisseur(fournisseur);

        // paiement entity to DB
        Paiement newPaiement =  paiementRepository.save(paiement);

        return mapToDTO(newPaiement);
	}

	@Override
	public List<PaiementDto> getPaiementsByFournisseurId(Long fournisseurId) {
		// retrieve paiements by fournisseurId
        List<Paiement> paiements = paiementRepository.findByFournisseurId(fournisseurId);

        // convert list of paiement entities to list of paiement dto's
        return paiements.stream().map(paiement -> mapToDTO(paiement)).collect(Collectors.toList());
	}

	@Override
	public PaiementDto getPaiementById(Long fournisseurId, Long paiementId) {
		// retrieve fournisseur entity by id
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElseThrow(
                () -> new ResourceNotFoundException("Fournisseur", "id", fournisseurId));

        // retrieve paiement by id
        Paiement paiement = paiementRepository.findById(paiementId).orElseThrow(() ->
                new ResourceNotFoundException("Paiement", "id", paiementId));

        if(!paiement.getFournisseur().getId().equals(fournisseur.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Paiement does not belong to fournisseur");
        }

        return mapToDTO(paiement);
	}

	@Override
	public PaiementDto updatePaiement(Long fournisseurId, Long paiementId, PaiementDto paiementDto) {
		// retrieve fournisseur entity by id
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElseThrow(
                () -> new ResourceNotFoundException("Fournisseur", "id", fournisseurId));

        // retrieve paiement by id
        Paiement paiement = paiementRepository.findById(paiementId).orElseThrow(() ->
                new ResourceNotFoundException("Paiement", "id", paiementId));

        if(!paiement.getFournisseur().getId().equals(fournisseur.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Paiement does not belongs to fournisseur");
        }

        paiement.setMontant(paiementDto.getMontant());
        paiement.setQte(paiementDto.getQte());
        paiement.setDate(paiementDto.getDate());
        paiement.setPayee(paiementDto.isPayee());
        
        Paiement updatedPaiement = paiementRepository.save(paiement);
        return mapToDTO(updatedPaiement);
	}

	@Override
	public void deletePaiement(Long fournisseurId, Long paiementId) {
		// retrieve fournisseur entity by id
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElseThrow(
                () -> new ResourceNotFoundException("Fournisseur", "id", fournisseurId));

        // retrieve paiement by id
        Paiement paiement = paiementRepository.findById(paiementId).orElseThrow(() ->
                new ResourceNotFoundException("Paiement", "id", paiementId));

        if(!paiement.getFournisseur().getId().equals(fournisseur.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Paiement does not belongs to fournisseur");
        }

        paiementRepository.delete(paiement);
	}

	private PaiementDto mapToDTO(Paiement paiement){
	     //   PaiementDto paiementDto = mapper.map(paiement, PaiementDto.class);

	        PaiementDto paiementDto = new PaiementDto();
	        paiementDto.setId(paiement.getId());
	        paiementDto.setMontant(paiement.getMontant());
	        paiementDto.setPayee(paiement.isPayee());
	        paiementDto.setQte(paiement.getQte());
	        return  paiementDto;
	    }

	    private Paiement mapToEntity(PaiementDto paiementDto){
	        //Paiement paiement = mapper.map(paiementDto, Paiement.class);
	        Paiement paiement = new Paiement();
	        paiement.setId(paiementDto.getId());
	        paiement.setMontant(paiementDto.getMontant());
	        paiement.setQte(paiementDto.getQte());
	        paiement.setPayee(paiementDto.isPayee());
	        return  paiement;
	    }

		@Override
		public PaiementResponse searchPaiementFull(int pageNo, int pageSize, String sortBy, String sortDir,
				String keywords) {
			Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
	                : Sort.by(sortBy).descending();

	        // create Pageable instance
	        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
	        
	        Page<Paiement> paiements = paiementRepository.findAll(pageable, keywords);
	        
	        List<Paiement> listOfPaiements = paiements.getContent();
	        
	        List<PaiementDto> content = listOfPaiements.stream().map(categoryFour -> mapToDTO(categoryFour)).collect(Collectors.toList());
			
	        PaiementResponse paiementResponse = new PaiementResponse();
	        paiementResponse.setContent(content);
	        paiementResponse.setPageNo(paiements.getNumber());
	        paiementResponse.setPageSize(paiements.getSize());
	        paiementResponse.setTotalElements(paiements.getTotalElements());
	        paiementResponse.setTotalPages(paiements.getTotalPages());
	        paiementResponse.setLast(paiements.isLast());

	        return paiementResponse;
		}
}
