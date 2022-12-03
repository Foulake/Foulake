package com.example.Fenalait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.MagasinRequestDto;
import com.example.Fenalait.dto.MagasinResponse;
import com.example.Fenalait.exception.BlogAPIException;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.model.Localite;
import com.example.Fenalait.model.Magasin;
import com.example.Fenalait.repository.LocaliteRepository;
import com.example.Fenalait.repository.MagasinRepository;
import com.example.Fenalait.service.MagasinService;

@Service
public class MagasinServiceImpl implements MagasinService{
	
private MagasinRepository magasinRepository; 
private LocaliteRepository localiteRepository;
	

	public MagasinServiceImpl(MagasinRepository magasinRepository, LocaliteRepository localiteRepository) {
	super();
	this.magasinRepository = magasinRepository;
	this.localiteRepository = localiteRepository;
}

	@Override
	public MagasinRequestDto createMagasin(MagasinRequestDto magasinRequestDto) {
		 // convert DTO to entity
        Magasin magasin = mapToEntity(magasinRequestDto);
        Magasin newMagasin = magasinRepository.save(magasin);

        // convert entity to DTO
        MagasinRequestDto magasinResponse = mapToDTO(newMagasin);
        return magasinResponse;
	}

	@Override
	public Magasin getMagasin(Long magasinId) {
		return magasinRepository.findById(magasinId).orElseThrow(
		() -> new ResourceNotFoundException("Il n'existe pas de magasin avec id : " + magasinId, null, magasinId));	
	}
	
	@Override
	public MagasinResponse getAllMagasins(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Magasin> magasins = magasinRepository.findAll(pageable);

        // get content for page object
        List<Magasin> listOfMagasins = magasins.getContent();

        List<MagasinRequestDto> content= listOfMagasins.stream().map(magasin -> mapToDTO(magasin)).collect(Collectors.toList());

        MagasinResponse magasinResponse = new MagasinResponse();
        magasinResponse.setContent(content);
        magasinResponse.setPageNo(magasins.getNumber());
        magasinResponse.setPageSize(magasins.getSize());
        magasinResponse.setTotalElements(magasins.getTotalElements());
        magasinResponse.setTotalPages(magasins.getTotalPages());
        magasinResponse.setLast(magasins.isLast());

        return magasinResponse;
	}

	@Override
	public MagasinRequestDto getMagasinById(Long id) {
		Magasin magasin = magasinRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Magasin", "id", id));
        return mapToDTO(magasin);
	}

	@Override
	public MagasinRequestDto updateMagasin(MagasinRequestDto magasinRequestDto, Long id) {
		// get magasin by id from the database
        Magasin magasin = magasinRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Magasin", "id", id));

        magasin.setNomMagasin(magasinRequestDto.getNomMagasin());

        Magasin updatedMagasin = magasinRepository.save(magasin);
        return mapToDTO(updatedMagasin);
	}

	@Override
	public void deleteMagasinById(Long id) {
		// get magasin by id from the database
        Magasin magasin = magasinRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Magasin", "id", id));
        magasinRepository.delete(magasin);
	}

	
	 // convert Entity into DTO
    private MagasinRequestDto mapToDTO(Magasin magasin){
       // MagasinRequestDto magasinRequestDto = mapper.map(magasin, MagasinRequestDto.class);
        MagasinRequestDto magasinRequestDto = new MagasinRequestDto();
        magasinRequestDto.setId(magasin.getId());
        magasinRequestDto.setNomMagasin(magasin.getNomMagasin());
        return magasinRequestDto;
    }

    // convert DTO to entity
    private Magasin mapToEntity(MagasinRequestDto magasinRequestDto){
        //Magasin magasin = mapper.map(magasinRequestDto, Magasin.class);
        Magasin magasin = new Magasin();
        magasin.setNomMagasin(magasinRequestDto.getNomMagasin());
        return magasin;
    }
    
    
    
    /** new codes **/

	@Override
	public MagasinRequestDto createMagasin(Long localiteId, MagasinRequestDto magasinRequestDto) {
		Magasin magasin = mapToEntity(magasinRequestDto);

        // retrieve localite entity by id
        Localite localite = localiteRepository.findById(localiteId).orElseThrow(
                () -> new ResourceNotFoundException("Localite", "id", localiteId));

        // set localite to magasin entity
        magasin.setLocalite(localite);

        // magasin entity to DB
        Magasin newMagasin =  magasinRepository.save(magasin);

        return mapToDTO(newMagasin);
	}

	@Override
	public List<MagasinRequestDto> getMagasinsByLocaliteId(Long localiteId) {
		// retrieve magasins by localiteId
        List<Magasin> magasins = magasinRepository.findByLocaliteId(localiteId);

        // convert list of magasin entities to list of magasin dto's
        return magasins.stream().map(magasin -> mapToDTO(magasin)).collect(Collectors.toList());
	}

	@Override
	public MagasinRequestDto getMagasinById(Long localiteId, Long magasinId) {
		// retrieve localite entity by id
        Localite localite = localiteRepository.findById(localiteId).orElseThrow(
                () -> new ResourceNotFoundException("Localite", "id", localiteId));

        // retrieve magasin by id
        Magasin magasin = magasinRepository.findById(magasinId).orElseThrow(() ->
                new ResourceNotFoundException("Magasin", "id", magasinId));

        if(!magasin.getLocalite().getId().equals(localite.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Magasin does not belong to localite");
        }

        return mapToDTO(magasin);
	}

	@Override
	public MagasinRequestDto updateMagasin(Long localiteId, Long magasinId, MagasinRequestDto magasinRequestDto) {
		// retrieve localite entity by id
        Localite localite = localiteRepository.findById(localiteId).orElseThrow(
                () -> new ResourceNotFoundException("Localite", "id", localiteId));

        // retrieve magasin by id
        Magasin magasin = magasinRepository.findById(magasinId).orElseThrow(() ->
                new ResourceNotFoundException("Magasin", "id", magasinId));

        if(!magasin.getLocalite().getId().equals(localite.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Magasin does not belongs to localite");
        }

        magasin.setNomMagasin(magasinRequestDto.getNomMagasin());
        
        Magasin updatedMagasin = magasinRepository.save(magasin);
        return mapToDTO(updatedMagasin);
	}

	@Override
	public void deleteMagasin(Long localiteId, Long magasinId) {
		// retrieve localite entity by id
        Localite localite = localiteRepository.findById(localiteId).orElseThrow(
                () -> new ResourceNotFoundException("Localite", "id", localiteId));

        // retrieve magasin by id
        Magasin magasin = magasinRepository.findById(magasinId).orElseThrow(() ->
                new ResourceNotFoundException("Magasin", "id", magasinId));

        if(!magasin.getLocalite().getId().equals(localite.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Magasin does not belongs to localite");
        }

        magasinRepository.delete(magasin);
	}

}
