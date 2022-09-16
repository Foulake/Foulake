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

import com.example.Fenalait.dto.ProduitResponse;
import com.example.Fenalait.dto.ProduitDto;
import com.example.Fenalait.service.ProduitService;
import com.example.Fenalait.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/produits")
public class ProduitController {
	
	 private ProduitService produitService;

	    public ProduitController(ProduitService produitService) {
	        this.produitService = produitService;
	    }

	    @PostMapping("/category/{categoryId}/produit")
	    public ResponseEntity<ProduitDto> createProduit(@PathVariable(value = "categoryId") Long categoryId,
	                                                    @Valid @RequestBody ProduitDto produitDto){
	        return new ResponseEntity<>(produitService.createProduit(categoryId, produitDto), HttpStatus.CREATED);
	    }

	    @GetMapping("/category/{categoryId}/produit")
	    public List<ProduitDto> getProduitsByCategoryId(@PathVariable(value = "categoryId") Long categoryId){
	        return produitService.getProduitsByCategoryId(categoryId);
	    }

	    @GetMapping("/category/{categoryId}/produit/{id}")
	    public ResponseEntity<ProduitDto> getProduitById(@PathVariable(value = "categoryId") Long categoryId,
	                                                     @PathVariable(value = "id") Long produitId){
	        ProduitDto produitDto = produitService.getProduitById(categoryId, produitId);
	        return new ResponseEntity<>(produitDto, HttpStatus.OK);
	    }

	    @PutMapping("/category/{categoryId}/produit/{id}")
	    public ResponseEntity<ProduitDto> updateProduit(@PathVariable(value = "categoryId") Long categoryId,
	                                                    @PathVariable(value = "id") Long produitId,
	                                                    @Valid @RequestBody ProduitDto produitDto){
	        ProduitDto updatedProduit = produitService.updateProduit(categoryId, produitId, produitDto);
	        return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
	    }

	    @DeleteMapping("/category/{categoryId}/produit/{id}")
	    public ResponseEntity<String> deleteProduit(@PathVariable(value = "categoryId") Long categoryId,
	                                                @PathVariable(value = "id") Long produitId){
	        produitService.deleteProduit(categoryId, produitId);
	        return new ResponseEntity<>("Produit deleted successfully", HttpStatus.OK);
	    }

	    
	    /** Pour Magasin **/
	    
	    @PostMapping("/magasin/{magasinId}/produit")
	    public ResponseEntity<ProduitDto> createMagasinProduit(@PathVariable(value = "magasinId") Long magasinId,
	                                                    @Valid @RequestBody ProduitDto produitDto){
	        return new ResponseEntity<>(produitService.createProduit(magasinId, produitDto), HttpStatus.CREATED);
	    }

	    @GetMapping("/magasin/{magasinId}/produit")
	    public List<ProduitDto> getProduitsByMagasinId(@PathVariable(value = "magasinId") Long magasinId){
	        return produitService.getProduitsByMagasinId(magasinId);
	    }

	    @GetMapping("/magasin/{magasinId}/produit/{id}")
	    public ResponseEntity<ProduitDto> getMagasinProduitById(@PathVariable(value = "magasinId") Long magasinId,
	                                                     @PathVariable(value = "id") Long produitId){
	        ProduitDto produitDto = produitService.getProduitById(magasinId, produitId);
	        return new ResponseEntity<>(produitDto, HttpStatus.OK);
	    }

	    @PutMapping("/magasin/{magasinId}/produit/{id}")
	    public ResponseEntity<ProduitDto> updateMagasinProduit(@PathVariable(value = "magasinId") Long magasinId,
	                                                    @PathVariable(value = "id") Long produitId,
	                                                    @Valid @RequestBody ProduitDto produitDto){
	        ProduitDto updatedProduit = produitService.updateProduit(magasinId, produitId, produitDto);
	        return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
	    }

	    @DeleteMapping("/magasin/{magasinId}/produit/{id}")
	    public ResponseEntity<String> deleteMagasinProduit(@PathVariable(value = "magasinId") Long magasinId,
	                                                @PathVariable(value = "id") Long produitId){
	        produitService.deleteProduit(magasinId, produitId);
	        return new ResponseEntity<>("Produit deleted successfully", HttpStatus.OK);
	    }
	    
	    @GetMapping("/search/full/{keywords}")
		public  ResponseEntity<ProduitResponse> searchProductByFull(
				 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
		            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
		            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
		            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
				@PathVariable("keywords") String keywords){
			ProduitResponse result= produitService.searchProduitFull(pageNo, pageSize, sortBy, sortDir, keywords);
					
			return new ResponseEntity<ProduitResponse>(result, HttpStatus.OK);
	 }
}
