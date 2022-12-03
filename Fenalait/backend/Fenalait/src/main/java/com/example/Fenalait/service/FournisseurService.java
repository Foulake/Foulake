package com.example.Fenalait.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.FournisseurDto;
import com.example.Fenalait.model.Fournisseur;

@Service
public interface FournisseurService {

	FournisseurDto createFournisseur(Long categoryFourId, FournisseurDto fournisseurDto);

	 List<FournisseurDto> getFournisseursByCategoryFourId(Long categoryFourId);

	 FournisseurDto getFournisseurById(Long categoryFourId, Long fournisseurId);

	 FournisseurDto updateFournisseur(Long categoryFourId, Long fournisseurId, FournisseurDto fournisseurDto);

	 void deleteFournisseur(Long categoryFourId, Long fournisseurId);

	Fournisseur getFournisseur(Long fournisseurId);
}
