package com.example.Fenalait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.Fenalait.dto.FournisseurDto;
import com.example.Fenalait.exception.BlogAPIException;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.model.CategorieFournisseur;
import com.example.Fenalait.model.Fournisseur;
import com.example.Fenalait.repository.CategorieFournisseurRepository;
import com.example.Fenalait.repository.FournisseurRepository;
import com.example.Fenalait.service.FournisseurService;

@Service
public class FournisseurServiceImpl implements FournisseurService{
	
private FournisseurRepository fournisseurRepository;
	
	private CategorieFournisseurRepository categoryFourRepository;
	
	

	public FournisseurServiceImpl(FournisseurRepository fournisseurRepository, CategorieFournisseurRepository categoryFourRepository) {
		super();
		this.fournisseurRepository = fournisseurRepository;
		this.categoryFourRepository = categoryFourRepository;
	}

	@Override
	public FournisseurDto createFournisseur(Long categoryFourId, FournisseurDto fournisseurDto) {
		Fournisseur fournisseur = mapToEntity(fournisseurDto);

        // retrieve categoryFour entity by id
        CategorieFournisseur categoryFour = categoryFourRepository.findById(categoryFourId).orElseThrow(
                () -> new ResourceNotFoundException("CategoryFour", "id", categoryFourId));

        // set categoryFour to fournisseur entity
        fournisseur.setCategorieFournisseur(categoryFour);

        // fournisseur entity to DB
        Fournisseur newFournisseur =  fournisseurRepository.save(fournisseur);

        return mapToDTO(newFournisseur);
	}

	@Override
	public List<FournisseurDto> getFournisseursByCategoryFourId(Long categoryFourId) {
		// retrieve fournisseurs by categoryFourId
        List<Fournisseur> fournisseurs = fournisseurRepository.findByCategorieFournisseurId(categoryFourId);

        // convert list of fournisseur entities to list of fournisseur dto's
        return fournisseurs.stream().map(fournisseur -> mapToDTO(fournisseur)).collect(Collectors.toList());
	}

	@Override
	public FournisseurDto getFournisseurById(Long categoryFourId, Long fournisseurId) {
		// retrieve categoryFour entity by id
        CategorieFournisseur categoryFour = categoryFourRepository.findById(categoryFourId).orElseThrow(
                () -> new ResourceNotFoundException("CategoryFour", "id", categoryFourId));

        // retrieve fournisseur by id
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElseThrow(() ->
                new ResourceNotFoundException("Fournisseur", "id", fournisseurId));

        if(!fournisseur.getCategorieFournisseur().getId().equals(categoryFour.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Fournisseur does not belong to categoryFour");
        }

        return mapToDTO(fournisseur);
	}

	@Override
	public FournisseurDto updateFournisseur(Long categoryFourId, Long fournisseurId, FournisseurDto fournisseurDto) {
		// retrieve categoryFour entity by id
        CategorieFournisseur categoryFour = categoryFourRepository.findById(categoryFourId).orElseThrow(
                () -> new ResourceNotFoundException("CategoryFour", "id", categoryFourId));

        // retrieve fournisseur by id
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElseThrow(() ->
                new ResourceNotFoundException("Fournisseur", "id", fournisseurId));

        if(!fournisseur.getCategorieFournisseur().getId().equals(categoryFour.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Fournisseur does not belongs to categoryFour");
        }

        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setTel(fournisseurDto.getTel());
        
        Fournisseur updatedFournisseur = fournisseurRepository.save(fournisseur);
        return mapToDTO(updatedFournisseur);
	}

	@Override
	public void deleteFournisseur(Long categoryFourId, Long fournisseurId) {
		// retrieve categoryFour entity by id
        CategorieFournisseur categoryFour = categoryFourRepository.findById(categoryFourId).orElseThrow(
                () -> new ResourceNotFoundException("CategoryFour", "id", categoryFourId));

        // retrieve fournisseur by id
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElseThrow(() ->
                new ResourceNotFoundException("Fournisseur", "id", fournisseurId));

        if(!fournisseur.getCategorieFournisseur().getId().equals(categoryFour.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Fournisseur does not belongs to categoryFour");
        }

        fournisseurRepository.delete(fournisseur);
	}

	
	private FournisseurDto mapToDTO(Fournisseur fournisseur){
     //   FournisseurDto fournisseurDto = mapper.map(fournisseur, FournisseurDto.class);

        FournisseurDto fournisseurDto = new FournisseurDto();
        fournisseurDto.setId(fournisseur.getId());
        fournisseurDto.setNom(fournisseur.getNom());
        fournisseurDto.setPrenom(fournisseur.getPrenom());
        fournisseurDto.setTel(fournisseur.getTel());
        return  fournisseurDto;
    }

    private Fournisseur mapToEntity(FournisseurDto fournisseurDto){
        //Fournisseur fournisseur = mapper.map(fournisseurDto, Fournisseur.class);
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setTel(fournisseurDto.getTel());
        return  fournisseur;
    }
	

}
