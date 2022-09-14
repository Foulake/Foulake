package com.example.Fenalait.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fenalait.dto.CategoryFourDto;
import com.example.Fenalait.dto.CategoryFourResponse;
import com.example.Fenalait.service.CategoryFourService;
import com.example.Fenalait.utils.AppConstants;

@RestController
public class CategoryFourController {

	private CategoryFourService categoryFourService;

    public CategoryFourController(CategoryFourService categoryFourService) {
        this.categoryFourService = categoryFourService;
    }

   
    @PreAuthorize("hasRole('ADMIN')")
    // create blog categoryFour rest api
    @PostMapping("/api/v1/categoryFours")
    public ResponseEntity<CategoryFourDto> createCategoryFour(@Valid @RequestBody CategoryFourDto categoryFourDto){
        return new ResponseEntity<>(categoryFourService.createCategoryFour(categoryFourDto), HttpStatus.CREATED);
    }

    // get all categoryFours rest api
    @GetMapping("/getAll")
    public CategoryFourResponse getAllCategoryFours(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return categoryFourService.getAllCategories(pageNo, pageSize, sortBy, sortDir);
    }

    // get categoryFour by id
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<CategoryFourDto> getCategoryFourById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(categoryFourService.getCategoryFourById(id));
    }

    @PostAuthorize("hasRole('ADMIN')")
    // update categoryFour by id rest api
    @PutMapping("/edit/{id}")
    public ResponseEntity<CategoryFourDto> updateCategoryFour(@Valid @RequestBody CategoryFourDto categoryFourDto, @PathVariable(name = "id") Long id){

       CategoryFourDto categoryFourResponse = categoryFourService.updateCategoryFour(categoryFourDto, id);

       return new ResponseEntity<>(categoryFourResponse, HttpStatus.OK);
    }

    @PostAuthorize("hasRole('ADMIN')")
    // delete categoryFour rest api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoryFour(@PathVariable(name = "id") Long id){

        categoryFourService.deleteCategoryFourById(id);

        return new ResponseEntity<>("CategoryFour entity deleted successfully.", HttpStatus.OK);
    }
}
