package com.example.Fenalait.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.VenteDto;

@Service
public interface VenteService {

	VenteDto createVente(Long clientId, VenteDto venteDto);

	 List<VenteDto> getVentesByClientId(Long clientId);

	 VenteDto getVenteById(Long clientId, Long venteId);

	 VenteDto updateVente(Long clientId, Long venteId, VenteDto venteDto);

	 void deleteVente(Long clientId, Long venteId);
	 
	 
	 VenteDto createProduitVente(Long produitId, VenteDto venteDto);

	 List<VenteDto> getVentesByProduitId(Long produitId);

	 VenteDto getProduitVenteById(Long produitId, Long venteId);

	 VenteDto updateProduitVente(Long produitId, Long venteId, VenteDto venteDto);

	 void deleteProduitVente(Long produitId, Long venteId);
	 
}
