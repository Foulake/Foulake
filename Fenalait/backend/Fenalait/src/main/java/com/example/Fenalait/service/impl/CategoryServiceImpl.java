package com.example.Fenalait.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.CategoryDto;
import com.example.Fenalait.dto.CategoryResponse;
import com.example.Fenalait.exception.ResourceNotFoundException;
import com.example.Fenalait.model.Category;
import com.example.Fenalait.repository.CategoryRepository;
import com.example.Fenalait.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepository categoryRepository; 
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		 // convert DTO to entity
        Category category = mapToEntity(categoryDto);
        Category newCategory = categoryRepository.save(category);

        // convert entity to DTO
        CategoryDto categoryResponse = mapToDTO(newCategory);
        return categoryResponse;
	}
	
	@Override
	public Category getCategory(Long categoryId) {
		return categoryRepository.findById(categoryId).orElseThrow(
		() ->  new ResourceNotFoundException("Il n'existe pas une categorie avec id : " + categoryId, null, categoryId));	
	}

	@Override
	public CategoryResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Category> categorys = categoryRepository.findAll(pageable);

        // get content for page object
        List<Category> listOfCategorys = categorys.getContent();

        List<CategoryDto> content= listOfCategorys.stream().map(category -> mapToDTO(category)).collect(Collectors.toList());

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(content);
        categoryResponse.setPageNo(categorys.getNumber());
        categoryResponse.setPageSize(categorys.getSize());
        categoryResponse.setTotalElements(categorys.getTotalElements());
        categoryResponse.setTotalPages(categorys.getTotalPages());
        categoryResponse.setLast(categorys.isLast());

        return categoryResponse;
	}

	@Override
	public CategoryDto getCategoryById(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return mapToDTO(category);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
		// get category by id from the database
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        category.setNom(categoryDto.getNom());

        Category updatedCategory = categoryRepository.save(category);
        return mapToDTO(updatedCategory);
	}

	@Override
	public void deleteCategoryById(Long id) {
		// get category by id from the database
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
	}

	
	 // convert Entity into DTO
    private CategoryDto mapToDTO(Category category){
       // CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setNom(category.getNom());
        return categoryDto;
    }

    // convert DTO to entity
    private Category mapToEntity(CategoryDto categoryDto){
        //Category category = mapper.map(categoryDto, Category.class);
        Category category = new Category();
        category.setNom(categoryDto.getNom());
        return category;
    }

	@Override
	public CategoryResponse searchCategoryFull(int pageNo, int pageSize, String sortBy, String sortDir,
			String keywords) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Category> categoryFours = categoryRepository.findAll(pageable, keywords);
        
        List<Category> listOfCategoryFours = categoryFours.getContent();
        
        List<CategoryDto> content = listOfCategoryFours.stream().map(categoryFour -> mapToDTO(categoryFour)).collect(Collectors.toList());
		
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(content);
        categoryResponse.setPageNo(categoryFours.getNumber());
        categoryResponse.setPageSize(categoryFours.getSize());
        categoryResponse.setTotalElements(categoryFours.getTotalElements());
        categoryResponse.setTotalPages(categoryFours.getTotalPages());
        categoryResponse.setLast(categoryFours.isLast());

        return categoryResponse;
	}
}
