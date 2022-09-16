package com.example.Fenalait.controller;

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

import com.example.Fenalait.dto.CategoryDto;
import com.example.Fenalait.dto.CategoryResponse;
import com.example.Fenalait.dto.CategoryResponse;
import com.example.Fenalait.service.CategoryService;
import com.example.Fenalait.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

   
    //@PreAuthorize("hasRole('ADMIN')")
    // create blog category rest api
    @PostMapping("/add")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    // get all categorys rest api
    @GetMapping("/getAll")
    public CategoryResponse getAllCategorys(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return categoryService.getAllCategories(pageNo, pageSize, sortBy, sortDir);
    }

    // get category by id
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    //@PostAuthorize("hasRole('ADMIN')")
    // update category by id rest api
    @PutMapping("/edit/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable(name = "id") long id){

       CategoryDto categoryResponse = categoryService.updateCategory(categoryDto, id);

       return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    //@PostAuthorize("hasRole('ADMIN')")
    // delete category rest api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") long id){

        categoryService.deleteCategoryById(id);

        return new ResponseEntity<>("Category entity deleted successfully.", HttpStatus.OK);
    }
    
    @GetMapping("/search/full/{keywords}")
	public  ResponseEntity<CategoryResponse> searchCategoryByFull(
			 @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
	            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@PathVariable("keywords") String keywords){
		CategoryResponse result= categoryService.searchCategoryFull(pageNo, pageSize, sortBy, sortDir, keywords);
				
		return new ResponseEntity<CategoryResponse>(result, HttpStatus.OK);
    
    }
}