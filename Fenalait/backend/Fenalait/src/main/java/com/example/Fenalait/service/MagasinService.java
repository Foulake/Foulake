package com.example.Fenalait.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.MagasinRequestDto;
import com.example.Fenalait.dto.MagasinResponse;
import com.example.Fenalait.model.Magasin;

@Service
public interface MagasinService {
	
	MagasinRequestDto createMagasin(MagasinRequestDto MagasinDto);

    MagasinResponse getAllMagasins(int pageNo, int pageSize, String sortBy, String sortDir);

    MagasinRequestDto getMagasinById(Long id);

    MagasinRequestDto updateMagasin(MagasinRequestDto MagasinDto, Long id);

    void deleteMagasinById(Long id);
    
    // New Codes

    MagasinRequestDto createMagasin(Long localiteId, MagasinRequestDto MagasinRequestDto);

	 List<MagasinRequestDto> getMagasinsByLocaliteId(Long localiteId);

	 MagasinRequestDto getMagasinById(Long localiteId, Long magasinId);

	 MagasinRequestDto updateMagasin(Long localiteId, Long magasinId, MagasinRequestDto MagasinRequestDto);

	 void deleteMagasin(Long localiteId, Long magasinId);

	Magasin getMagasin(Long categoryId);
}
