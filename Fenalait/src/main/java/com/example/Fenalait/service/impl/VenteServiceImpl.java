package com.example.Fenalait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.VenteDto;
import com.example.Fenalait.exception.BlogAPIException;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.model.Client;
import com.example.Fenalait.model.Produit;
import com.example.Fenalait.model.Vente;
import com.example.Fenalait.repository.ClientRepository;
import com.example.Fenalait.repository.ProduitRepository;
import com.example.Fenalait.repository.VenteRepository;
import com.example.Fenalait.service.VenteService;

@Service
public class VenteServiceImpl implements VenteService{

private VenteRepository venteRepository;
	
	private ClientRepository clientRepository;
	
	private  ProduitRepository produitRepository;
	

	public VenteServiceImpl(VenteRepository venteRepository, ClientRepository clientRepository, ProduitRepository produitRepository) {
		super();
		this.venteRepository = venteRepository;
		this.clientRepository = clientRepository;
		this.produitRepository = produitRepository;
	}

	@Override
	public VenteDto createVente(Long clientId, VenteDto venteDto) {
		Vente vente = mapToEntity(venteDto);

        // retrieve client entity by id
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Client", "id", clientId));

        // set client to vente entity
        vente.setClient(client);

        // vente entity to DB
        Vente newVente =  venteRepository.save(vente);

        return mapToDTO(newVente);
	}

	@Override
	public List<VenteDto> getVentesByClientId(Long clientId) {
		// retrieve ventes by clientId
        List<Vente> ventes = venteRepository.findByClientId(clientId);

        // convert list of vente entities to list of vente dto's
        return ventes.stream().map(vente -> mapToDTO(vente)).collect(Collectors.toList());
	}

	@Override
	public VenteDto getVenteById(Long clientId, Long venteId) {
		// retrieve client entity by id
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Client", "id", clientId));

        // retrieve vente by id
        Vente vente = venteRepository.findById(venteId).orElseThrow(() ->
                new ResourceNotFoundException("Vente", "id", venteId));

        if(!vente.getClient().getId().equals(client.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Vente does not belong to client");
        }

        return mapToDTO(vente);
	}

	@Override
	public VenteDto updateVente(Long clientId, Long venteId, VenteDto venteDto) {
		// retrieve client entity by id
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Client", "id", clientId));

        // retrieve vente by id
        Vente vente = venteRepository.findById(venteId).orElseThrow(() ->
                new ResourceNotFoundException("Vente", "id", venteId));

        if(!vente.getClient().getId().equals(client.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Vente does not belongs to client");
        }

        vente.setDate(venteDto.getDate());
        vente.setMontant(venteDto.getMontant());
        vente.setQuantite(venteDto.getQuantite());
        //vente.setRemise(venteDto.getRemise());
        
        Vente updatedVente = venteRepository.save(vente);
        return mapToDTO(updatedVente);
	}

	@Override
	public void deleteVente(Long clientId, Long venteId) {
		// retrieve client entity by id
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Client", "id", clientId));

        // retrieve vente by id
        Vente vente = venteRepository.findById(venteId).orElseThrow(() ->
                new ResourceNotFoundException("Vente", "id", venteId));

        if(!vente.getClient().getId().equals(client.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Vente does not belongs to client");
        }

        venteRepository.delete(vente);
	}

	
	/** Pour Produit **/
	
	@Override
	public VenteDto createProduitVente(Long produitId, VenteDto venteDto) {
		Vente vente = mapToEntity(venteDto);

        // retrieve produit entity by id
        Produit produit = produitRepository.findById(produitId).orElseThrow(
                () -> new ResourceNotFoundException("Produit", "id", produitId));

        // set produit to vente entity
        vente.setProduit(produit);

        // vente entity to DB
        Vente newVente =  venteRepository.save(vente);

        return mapToDTO(newVente);
	}

	@Override
	public List<VenteDto> getVentesByProduitId(Long produitId) {
		// retrieve ventes by produitId
        List<Vente> ventes = venteRepository.findByProduitId(produitId);

        // convert list of vente entities to list of vente dto's
        return ventes.stream().map(vente -> mapToDTO(vente)).collect(Collectors.toList());
	}

	@Override
	public VenteDto getProduitVenteById(Long produitId, Long venteId) {
		// retrieve produit entity by id
        Produit produit = produitRepository.findById(produitId).orElseThrow(
                () -> new ResourceNotFoundException("Produit", "id", produitId));

        // retrieve vente by id
        Vente vente = venteRepository.findById(venteId).orElseThrow(() ->
                new ResourceNotFoundException("Vente", "id", venteId));

        if(!vente.getProduit().getId().equals(produit.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Vente does not belong to produit");
        }

        return mapToDTO(vente);
	}

	@Override
	public VenteDto updateProduitVente(Long produitId, Long venteId, VenteDto venteDto) {
		// retrieve produit entity by id
        Produit produit = produitRepository.findById(produitId).orElseThrow(
                () -> new ResourceNotFoundException("Produit", "id", produitId));

        // retrieve vente by id
        Vente vente = venteRepository.findById(venteId).orElseThrow(() ->
                new ResourceNotFoundException("Vente", "id", venteId));

        if(!vente.getProduit().getId().equals(produit.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Vente does not belongs to produit");
        }

        vente.setDate(venteDto.getDate());
        vente.setMontant(venteDto.getMontant());
        vente.setQuantite(venteDto.getQuantite());
        //vente.setRemise(venteDto.getRemise());
        
        Vente updatedVente = venteRepository.save(vente);
        return mapToDTO(updatedVente);
	}

	@Override
	public void deleteProduitVente(Long produitId, Long venteId) {
		// retrieve produit entity by id
        Produit produit = produitRepository.findById(produitId).orElseThrow(
                () -> new ResourceNotFoundException("Produit", "id", produitId));

        // retrieve vente by id
        Vente vente = venteRepository.findById(venteId).orElseThrow(() ->
                new ResourceNotFoundException("Vente", "id", venteId));

        if(!vente.getProduit().getId().equals(produit.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Vente does not belongs to produit");
        }

        venteRepository.delete(vente);
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
