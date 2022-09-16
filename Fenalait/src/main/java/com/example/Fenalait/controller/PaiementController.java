package com.example.Fenalait.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fenalait.dto.PaiementResponse;
import com.example.Fenalait.dto.PaiementDto;
import com.example.Fenalait.service.PaiementService;
import com.example.Fenalait.utils.AppConstants;


@RestController
@RequestMapping("/api/v1/paiements")
public class PaiementController {

	private PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping("/fournisseur/{fournisseurId}/paiement")
    public ResponseEntity<PaiementDto> createPaiement(@PathVariable(value = "fournisseurId") Long fournisseurId,
                                                    @Valid @RequestBody PaiementDto paiementDto){
        return new ResponseEntity<>(paiementService.createPaiement(fournisseurId, paiementDto), HttpStatus.CREATED);
    }

    @GetMapping("/fournisseur/{fournisseurId}/paiement")
    public List<PaiementDto> getPaiementsByMagasinId(@PathVariable(value = "fournisseurId") Long fournisseurId){
        return paiementService.getPaiementsByFournisseurId(fournisseurId);
    }

    @GetMapping("/fournisseur/{fournisseurId}/paiement/{id}")
    public ResponseEntity<PaiementDto> getPaiementById(@PathVariable(value = "fournisseurId") Long fournisseurId,
                                                     @PathVariable(value = "id") Long paiementId){
        PaiementDto paiementDto = paiementService.getPaiementById(fournisseurId, paiementId);
        return new ResponseEntity<>(paiementDto, HttpStatus.OK);
    }

    @PutMapping("/fournisseur/{fournisseurId}/paiement/{id}")
    public ResponseEntity<PaiementDto> updatePaiement(@PathVariable(value = "fournisseurId") Long fournisseurId,
                                                    @PathVariable(value = "id") Long paiementId,
                                                    @Valid @RequestBody PaiementDto paiementDto){
        PaiementDto updatedPaiement = paiementService.updatePaiement(fournisseurId, paiementId, paiementDto);
        return new ResponseEntity<>(updatedPaiement, HttpStatus.OK);
    }

    @DeleteMapping("/fournisseur/{fournisseurId}/paiement/{id}")
    public ResponseEntity<String> deletePaiement(@PathVariable(value = "fournisseurId") Long fournisseurId,
                                                @PathVariable(value = "id") Long paiementId){
        paiementService.deletePaiement(fournisseurId, paiementId);
        return new ResponseEntity<>("Paiement deleted successfully", HttpStatus.OK);
    }
    
    @GetMapping("/search/full/{keywords}")
	public  ResponseEntity<PaiementResponse> searchPaiementByFull(
			 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
	            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@PathVariable("keywords") String keywords){
		PaiementResponse result= paiementService.searchPaiementFull(pageNo, pageSize, sortBy, sortDir, keywords);
				
		return new ResponseEntity<PaiementResponse>(result, HttpStatus.OK);
    
    }
}
