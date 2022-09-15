package com.example.Fenalait.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fenalait.dto.LocaliteRequestDto;
import com.example.Fenalait.dto.LocaliteResponse;
import com.example.Fenalait.service.LocaliteService;
import com.example.Fenalait.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/localites")
public class LocaliteController {

	private LocaliteService localiteService;

    public LocaliteController(LocaliteService localiteService) {
        this.localiteService = localiteService;
    }

    // create blog localite rest api
    @PostMapping("/add")
    public ResponseEntity<LocaliteRequestDto> createLocalite(@Valid @RequestBody LocaliteRequestDto localiteRequestDto){
        return new ResponseEntity<>(localiteService.createLocalite(localiteRequestDto), HttpStatus.CREATED);
    }

    // get all localites rest api
    @GetMapping("/getAll")
    public LocaliteResponse getAllLocalites(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return localiteService.getAllLocalites(pageNo, pageSize, sortBy, sortDir);
    }

    // get localite by id
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<LocaliteRequestDto> getLocaliteById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(localiteService.getLocaliteById(id));
    }

    //@PostAuthorize("hasRole('ADMIN')")
    // update localite by id rest api
    @PutMapping("/edit/{id}")
    public ResponseEntity<LocaliteRequestDto> updateLocalite(@Valid @RequestBody LocaliteRequestDto localiteRequestDto, @PathVariable(name = "id") Long id){

       LocaliteRequestDto localiteResponse = localiteService.updateLocalite(localiteRequestDto, id);

       return new ResponseEntity<>(localiteResponse, HttpStatus.OK);
    }

    @PostAuthorize("hasRole('ADMIN')")
    // delete localite rest api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLocalite(@PathVariable(name = "id") Long id){

        localiteService.deleteLocaliteById(id);

        return new ResponseEntity<>("Localite entity deleted successfully.", HttpStatus.OK);
    }
    

}
