package com.example.Fenalait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.LocaliteRequestDto;
import com.example.Fenalait.dto.LocaliteResponse;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.model.Localite;
import com.example.Fenalait.repository.LocaliteRepository;
import com.example.Fenalait.service.LocaliteService;

@Service
public class LocaliteServiceImpl implements LocaliteService{
	
	
private LocaliteRepository localiteRepository; 
	
	public LocaliteServiceImpl(LocaliteRepository localiteRepository) {
		this.localiteRepository = localiteRepository;
	}

	@Override
	public LocaliteRequestDto createLocalite(LocaliteRequestDto localiteRequestDto) {
		 // convert DTO to entity
        Localite localite = mapToEntity(localiteRequestDto);
        Localite newLocalite = localiteRepository.save(localite);

        // convert entity to DTO
        LocaliteRequestDto localiteResponse = mapToDTO(newLocalite);
        return localiteResponse;
	}

	@Override
	public LocaliteResponse getAllLocalites(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Localite> localites = localiteRepository.findAll(pageable);

        // get content for page object
        List<Localite> listOfLocalites = localites.getContent();

        List<LocaliteRequestDto> content= listOfLocalites.stream().map(localite -> mapToDTO(localite)).collect(Collectors.toList());

        LocaliteResponse localiteResponse = new LocaliteResponse();
        localiteResponse.setContent(content);
        localiteResponse.setPageNo(localites.getNumber());
        localiteResponse.setPageSize(localites.getSize());
        localiteResponse.setTotalElements(localites.getTotalElements());
        localiteResponse.setTotalPages(localites.getTotalPages());
        localiteResponse.setLast(localites.isLast());

        return localiteResponse;
	}

	@Override
	public LocaliteRequestDto getLocaliteById(Long id) {
		Localite localite = localiteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Localite", "id", id));
        return mapToDTO(localite);
	}

	@Override
	public LocaliteRequestDto updateLocalite(LocaliteRequestDto localiteRequestDto, Long id) {
		// get localite by id from the database
        Localite localite = localiteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Localite", "id", id));

        localite.setNom(localiteRequestDto.getNom());
        localite.setDescription(localiteRequestDto.getDescription());

        Localite updatedLocalite = localiteRepository.save(localite);
        return mapToDTO(updatedLocalite);
	}

	@Override
	public void deleteLocaliteById(Long id) {
		// get localite by id from the database
        Localite localite = localiteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Localite", "id", id));
        localiteRepository.delete(localite);
	}

	
	 // convert Entity into DTO
    private LocaliteRequestDto mapToDTO(Localite localite){
       // LocaliteRequestDto localiteRequestDto = mapper.map(localite, LocaliteRequestDto.class);
        LocaliteRequestDto localiteRequestDto = new LocaliteRequestDto();
        localiteRequestDto.setId(localite.getId());
        localiteRequestDto.setNom(localite.getNom());
        localiteRequestDto.setDescription(localite.getDescription());
        return localiteRequestDto;
    }

    // convert DTO to entity
    private Localite mapToEntity(LocaliteRequestDto localiteRequestDto){
        //Localite localite = mapper.map(localiteRequestDto, Localite.class);
        Localite localite = new Localite();
        localite.setNom(localiteRequestDto.getNom());
        localite.setDescription(localiteRequestDto.getDescription());
        return localite;
    }


}
