package com.example.Fenalait.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
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

import com.example.Fenalait.dto.ApproDto;
import com.example.Fenalait.dto.ApproResponse;
import com.example.Fenalait.model.Approvissionnement;
import com.example.Fenalait.service.ApproService;
import com.example.Fenalait.utils.AppConstants;


@RestController
@RequestMapping("/api/v1/approvissionnements")
public class ApproController {
	
	private ApproService approService;

    public ApproController(ApproService approService) {
        this.approService = approService;
    }

    @PostMapping("/add")
	//@RolesAllowed({"ROLE_ADMIN"})
	public ResponseEntity<ApproResponse> addProduct(@Valid @RequestBody ApproDto approDto){
		ApproResponse approResponse = approService.addApprovissionnement(approDto);
		return new ResponseEntity<ApproResponse>(approResponse, HttpStatus.OK);
	}
   
    @GetMapping("/get/{id}")
	public ResponseEntity<ApproResponse> getProduct(@PathVariable final Long approId){
    	ApproResponse approDto = approService.getApprovissionnementById(approId);
		return new ResponseEntity<ApproResponse>(approDto, HttpStatus.OK);
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
    

    @PutMapping("/edit/{id}")
	public ResponseEntity<ApproResponse> editProduct(@Valid @RequestBody final ApproDto approDto, @PathVariable final Long id){
    	ApproResponse approResponse = approService.editApprovissionnement(id, approDto);
		return new ResponseEntity<>(approResponse, HttpStatus.OK);
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
    
    @GetMapping("/approinterval={dateStart}/{dateEnd}")
	public ResponseEntity<List<Approvissionnement>> getApproJourIntervals(@PathVariable
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart, 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd ){
    	List<Approvissionnement> approvissionnements = approService.approvissionnementJourInterval(dateStart, dateEnd);
		return new ResponseEntity<>(approvissionnements, HttpStatus.OK);
	}

}
