package com.example.Fenalait.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.VenteDto;
import com.example.Fenalait.dto.VenteResponse;
import com.example.Fenalait.model.Vente;

@Service
public interface VenteService {

	public VenteResponse addVente(VenteDto venteDto );
	public VenteResponse getVenteById(Long productId);
	public Vente getVente(Long productId);
	public List<VenteResponse> getVentes();
	public VenteResponse getAllVentes(int pageNo, int pageSize, String sortBy, String sortDir);
	public VenteResponse deleteVente(Long productId);
	public VenteResponse editVente(Long productId, VenteDto venteDto);
	public VenteResponse searchVenteFull(int pageNo, int pageSize, String sortBy, String sortDir, String keyword);
	 
}
