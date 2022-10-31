package com.example.Fenalait.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fenalait.dto.EmployeDto;
import com.example.Fenalait.dto.EmployeResponse;
import com.example.Fenalait.service.EmployeService;
import com.example.Fenalait.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/employes")
public class EmployeController {

	private EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

   
    //@PreAuthorize("hasRole('ADMIN')")
    // create blog employe rest api
    @PostMapping("/add")
    public ResponseEntity<EmployeDto> createEmploye(@Valid @RequestBody EmployeDto employeDto){
        return new ResponseEntity<>(employeService.createEmploye(employeDto), HttpStatus.CREATED);
    }

    // get all employes rest api
    @GetMapping("/getAll")
    public EmployeResponse getAllEmployes(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return employeService.getAllEmployes(pageNo, pageSize, sortBy, sortDir);
    }

    // get employe by id
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<EmployeDto> getEmployeById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(employeService.getEmployeById(id));
    }

    //@PostAuthorize("hasRole('ADMIN')")
    // update employe by id rest api
    @PutMapping("/edit/{id}")
    public ResponseEntity<EmployeDto> updateEmploye(@Valid @RequestBody EmployeDto employeDto, @PathVariable(name = "id") Long id){

       EmployeDto employeResponse = employeService.updateEmploye(employeDto, id);

       return new ResponseEntity<>(employeResponse, HttpStatus.OK);
    }

    //@PostAuthorize("hasRole('ADMIN')")
    // delete employe rest api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmploye(@PathVariable(name = "id") Long id){

        employeService.deleteEmployeById(id);

        return new ResponseEntity<>("Employe entity deleted successfully.", HttpStatus.OK);
    }
    
	 @GetMapping("/search/full/{keywords}")
		public  ResponseEntity<EmployeResponse> searchProductByFull(
				 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
		            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
		            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
		            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
				@PathVariable("keywords") String keywords){
			EmployeResponse result= employeService.searchEmployeFull(pageNo, pageSize, sortBy, sortDir, keywords);
					
			return new ResponseEntity<EmployeResponse>(result, HttpStatus.OK);
	 }
}