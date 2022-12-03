package com.example.Fenalait.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.Mapper;
import com.example.Fenalait.dto.VenteDto;
import com.example.Fenalait.dto.VenteResponse;
import com.example.Fenalait.exception.BlogAPIException;
import com.example.Fenalait.exception.ResourceAlredyExistException;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.exception.ResourceNotFoundExceptions;
import com.example.Fenalait.model.Client;
import com.example.Fenalait.model.Produit;
import com.example.Fenalait.model.User;
import com.example.Fenalait.model.Vente;
import com.example.Fenalait.repository.ClientRepository;
import com.example.Fenalait.repository.ProduitRepository;
import com.example.Fenalait.repository.VenteRepository;
import com.example.Fenalait.service.ClientService;
import com.example.Fenalait.service.ProduitService;
import com.example.Fenalait.service.UserService;
import com.example.Fenalait.service.VenteService;

@Service
public class VenteServiceImpl implements VenteService{

private VenteRepository venteRepository;
	
	private ClientRepository clientRepository;
	
	private  ProduitRepository produitRepository;
	
	private ClientService clientService;
	
	private  ProduitService produitService;
	private  UserService userService;

	public VenteServiceImpl(VenteRepository venteRepository, ClientRepository clientRepository, ProduitRepository produitRepository,ProduitService produitService,ClientService clientService,UserService userService) {
		super();
		this.venteRepository = venteRepository;
		this.clientRepository = clientRepository;
		this.produitRepository = produitRepository;
		this.clientService = clientService;
		this.produitService = produitService;	
		this.userService = userService;		
		}

	@Transactional     
	@Override
	public VenteResponse addVente(VenteDto venteDto) {
		Vente vente = new Vente();
	
		vente.setMontant(venteDto.getMontant());
		vente.setQuantite(venteDto.getQuantite());
		vente.setDate(venteDto.getDate());
		vente.setRemise(venteDto.isRemise());
		
		if(venteDto.getClientId() == null ) {
			throw new IllegalArgumentException("Le vente manque de Categorie !");
		}
		if(venteDto.getProduitId() == null) {
			throw new IllegalArgumentException("Le vente manque de Produit !");
		}
		
		
		Client client = clientService.getClient(venteDto.getClientId());
		vente.setClient(client);
		
		Produit produit = produitService.getProduit(venteDto.getClientId());
		vente.setProduit(produit);
		
		User user = userService.getUser(venteDto.getUserId());;
		vente.setUser(user);
		
		vente.setMontant(vente.getMontant());
		vente.setQuantite(vente.getQuantite());
		vente.setMontant(venteDto.getQuantite()*vente.getProduit().getPrice());
		//vente.setMontant(vente.getMontant());
		vente.setRemise(vente.isRemise());
		vente.setDate(new Date());
		
		Vente vente1 = venteRepository.save(vente);
		return Mapper.venteToVenteResponse(vente1);
	}

	@Override
	public VenteResponse getVenteById(Long venteId) {
		Vente vente = getVente(venteId);
		return Mapper.venteToVenteResponse(vente);
	}

	@Override
	public Vente getVente(Long venteId) {
		Vente vente = venteRepository.findById(venteId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("Il n'existe pas de vente avec id : " + venteId));
		return vente;
	}
	
	
	@Override
	public VenteResponse deleteVente(Long venteId) {
		Vente vente = getVente(venteId);
		venteRepository.delete(vente);
		return Mapper.venteToVenteResponse(vente);
	}

	@Transactional
	@Override
	public VenteResponse editVente(Long venteId, VenteDto venteDto) {
		Vente venteEdit = getVente(venteId);
		
		venteEdit.setQuantite(venteDto.getQuantite());
		venteEdit.setMontant(venteDto.getQuantite()*venteEdit.getProduit().getPrice());
		venteEdit.setDate(new Date());
		venteEdit.setRemise(venteDto.isRemise());
		
		if(venteDto.getClientId() != null ) {
			Client client = clientService.getClient(venteDto.getClientId());
			venteEdit.setClient(client);
			
		}
		if(venteDto.getProduitId() != null ) {
			Produit produit = produitService.getProduit(venteDto.getProduitId());
			venteEdit.setProduit(produit);
		}
		
		if(venteDto.getUserId() != null ) {
			User user = userService.getUser(venteDto.getUserId());
			venteEdit.setUser(user);
		}
		
		return Mapper.venteToVenteResponse(venteEdit);
	}


	@Override
	public List<VenteResponse> getVentes() {
		List<Vente> ventes = StreamSupport
				.stream(venteRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return Mapper.venteToVenteResponses(ventes);
	}

	

	@Override
	public VenteResponse getAllVentes(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Vente> ventes = venteRepository.findAll(pageable);
        
        List<Vente> listOfVentes = ventes.getContent();
        
        List<VenteDto> content = listOfVentes.stream().map(vente -> mapToDTO(vente)).collect(Collectors.toList());
		
        VenteResponse venteResponse = new VenteResponse();
        venteResponse.setContent(content);
        venteResponse.setPageNo(ventes.getNumber());
        venteResponse.setPageSize(ventes.getSize());
        venteResponse.setTotalElements(ventes.getTotalElements());
        venteResponse.setTotalPages(ventes.getTotalPages());
        venteResponse.setLast(ventes.isLast());

        return venteResponse;
	}

	

	@Override
	public VenteResponse searchVenteFull(int pageNo, int pageSize, String sortBy, String sortDir, String keyword) {
		//List<Vente> ventes = this.venteRepository.findByName(keyword);
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Vente> ventes = venteRepository.findAll(pageable, keyword);
        
        List<Vente> listOfVentes = ventes.getContent();
        
        List<VenteDto> content = listOfVentes.stream().map(vente -> mapToDTO(vente)).collect(Collectors.toList());
		
        VenteResponse venteResponse = new VenteResponse();
        venteResponse.setContent(content);
        venteResponse.setPageNo(ventes.getNumber());
        venteResponse.setPageSize(ventes.getSize());
        venteResponse.setTotalElements(ventes.getTotalElements());
        venteResponse.setTotalPages(ventes.getTotalPages());
        venteResponse.setLast(ventes.isLast());

        return venteResponse;
	}
	
	private VenteDto mapToDTO(Vente vente){
     //   VenteDto venteDto = mapper.map(vente, VenteDto.class);

        VenteDto venteDto = new VenteDto();
        venteDto.setId(vente.getId());
        venteDto.setDate(vente.getDate());
        venteDto.setMontant(vente.getMontant());
        //venteDto.setRemise(vente.getRemise());
        venteDto.setQuantite(vente.getQuantite());
        return  venteDto;
    }

    private Vente mapToEntity(VenteDto venteDto){
        //Vente vente = mapper.map(venteDto, Vente.class);
        Vente vente = new Vente();
        vente.setId(venteDto.getId());
        vente.setDate(venteDto.getDate());
        vente.setQuantite(venteDto.getQuantite());
        //vente.setRemise(venteDto.getRemise());
        return  vente;
    }
    
}
