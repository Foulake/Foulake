package com.example.Fenalait.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.ApproDto;
import com.example.Fenalait.dto.ApproResponse;
import com.example.Fenalait.model.Approvissionnement;

@Service
public interface ApproService {
	
	
	
	public ApproResponse addApprovissionnement(ApproDto approDto );
	public ApproResponse getApprovissionnementById(Long approId);
	public Approvissionnement getApprovissionnement(Long approId);
	public List<ApproResponse> getApprovissionnements();
	public ApproResponse getAllApprovissionnements(int pageNo, int pageSize, String sortBy, String sortDir);
	public ApproResponse deleteApprovissionnement(Long approId);
	public ApproResponse editApprovissionnement(Long approId, ApproDto approDto);
	
	public ApproResponse searchApprovissionnementFull(int pageNo, int pageSize, String sortBy, String sortDir, String keyword);

	public List<Approvissionnement> approvissionnementJourInterval(LocalDate dateStart, LocalDate dateEnd );
	
}
