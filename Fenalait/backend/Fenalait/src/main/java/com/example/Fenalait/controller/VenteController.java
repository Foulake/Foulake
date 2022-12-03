package com.example.Fenalait.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.example.Fenalait.dto.VenteDto;
import com.example.Fenalait.dto.VenteResponse;
import com.example.Fenalait.service.VenteService;
import com.example.Fenalait.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/ventes")
public class VenteController {
	
	 private VenteService venteService;

	    public VenteController(VenteService venteService) {
	        this.venteService = venteService;
	    }

	    @PostMapping("/add")
		//@RolesAllowed({"ROLE_ADMIN"})
		public ResponseEntity<VenteResponse> addVente(@Valid @RequestBody VenteDto venteDto){
			VenteResponse venteResponseDto = venteService.addVente(venteDto);
			return new ResponseEntity<VenteResponse>(venteResponseDto, HttpStatus.OK);
		}
	   
	    @GetMapping("/get/{id}")
		public ResponseEntity<VenteResponse> getVente(@PathVariable final Long venteId){
	    	VenteResponse venteDto = venteService.getVenteById(venteId);
			return new ResponseEntity<VenteResponse>(venteDto, HttpStatus.OK);
		}
	    
	    @GetMapping("/getAll")
	    public VenteResponse getAllVentes(
	            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
	            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
	    ){
	        return venteService.getAllVentes(pageNo, pageSize, sortBy, sortDir);
	    }
	    

	    @PutMapping("edit/{id}")
		public ResponseEntity<VenteResponse> editVente(@Valid @RequestBody final VenteDto venteRequestDto, @PathVariable final Long id){
	    	VenteResponse venteResponseDto = venteService.editVente(id, venteRequestDto);
			return new ResponseEntity<>(venteResponseDto, HttpStatus.OK);
		}
	    
	    @DeleteMapping("/delete/{id}")
		//@RolesAllowed({"ROLE_ADMIN"})
		public ResponseEntity<Map<String, Boolean>> deletePrroduct(@PathVariable final Long id){
			venteService.deleteVente(id);
			Map<String, Boolean> response =new  HashMap<>();
	    	response.put("Le vente a été supprimé avec succès", Boolean.TRUE);
	    	return ResponseEntity.ok(response);
		
		}


	    @GetMapping("/search/full/{keywords}")
		public  ResponseEntity<VenteResponse> searchVenteByFull(
				 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
		            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
		            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
		            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
				@PathVariable("keywords") String keywords){
			VenteResponse result= venteService.searchVenteFull(pageNo, pageSize, sortBy, sortDir, keywords);
					
			return new ResponseEntity<VenteResponse>(result, HttpStatus.OK);
	 }
}