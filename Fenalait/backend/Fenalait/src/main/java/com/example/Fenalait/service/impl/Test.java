package com.example.Fenalait.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Fenalait.dto.ApproResponse;
import com.example.Fenalait.dto.Mapper;
import com.example.Fenalait.dto.ApproDto;
import com.example.Fenalait.exception.ResourceAlredyExistException;
import com.example.Fenalait.exception.ResourceNotFoundExceptions;
import com.example.Fenalait.model.Approvissionnement;
import com.example.Fenalait.model.Fournisseur;
import com.example.Fenalait.model.User;
import com.example.Fenalait.repository.ApproRepository;
import com.example.Fenalait.repository.FournisseurRepository;
import com.example.Fenalait.service.FournisseurService;
import com.example.Fenalait.service.ApproService;
import com.example.Fenalait.service.UserService;
import com.example.Fenalait.utils.AppConstants;

public class Test {
	 private ApproService approService;

	    public Test(ApproService approService) {
	        this.approService = approService;
	    }

	    @PostMapping("/add")
		//@RolesAllowed({"ROLE_ADMIN"})
		public ResponseEntity<ApproResponse> addProduct(@Valid @RequestBody ApproDto productDto){
			ApproResponse productResponseDto = approService.addApprovissionnement(productDto);
			return new ResponseEntity<ApproResponse>(productResponseDto, HttpStatus.OK);
		}
	   
	    @GetMapping("/get/{id}")
		public ResponseEntity<ApproResponse> getProduct(@PathVariable final Long productId){
	    	ApproResponse productDto = approService.getApprovissionnementById(productId);
			return new ResponseEntity<ApproResponse>(productDto, HttpStatus.OK);
		}
	    
	    @GetMapping("/getAll")
	    public ApproResponse getAllProducts(
	            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
	            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
	    ){
	        return approService.getAllApprovissionnements(pageNo, pageSize, sortBy, sortDir);
	    }
	    

	    @PutMapping("edit/{id}")
		public ResponseEntity<ApproResponse> editProduct(@Valid @RequestBody final ApproDto productRequestDto, @PathVariable final Long id){
	    	ApproResponse productResponseDto = approService.editApprovissionnement(id, productRequestDto);
			return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
		}
	    
	    @DeleteMapping("/delete/{id}")
		//@RolesAllowed({"ROLE_ADMIN"})
		public ResponseEntity<Map<String, Boolean>> deletePrroduct(@PathVariable final Long id){
			approService.deleteApprovissionnement(id);
			Map<String, Boolean> response =new  HashMap<>();
	    	response.put("Le approvissionnement a été supprimé avec succès", Boolean.TRUE);
	    	return ResponseEntity.ok(response);
		
		}


	    @GetMapping("/search/full/{keywords}")
		public  ResponseEntity<ApproResponse> searchProductByFull(
				 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
		            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
		            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
		            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
				@PathVariable("keywords") String keywords){
			ApproResponse result= approService.searchApprovissionnementFull(pageNo, pageSize, sortBy, sortDir, keywords);
					
			return new ResponseEntity<ApproResponse>(result, HttpStatus.OK);
	 }
	}
