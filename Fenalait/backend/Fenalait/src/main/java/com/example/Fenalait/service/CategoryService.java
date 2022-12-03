package com.example.Fenalait.service;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.CategoryDto;
import com.example.Fenalait.dto.CategoryResponse;
import com.example.Fenalait.model.Category;

@Service
public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

    CategoryResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir);

    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategory(CategoryDto categoryDto, Long id);

    void deleteCategoryById(Long id);

	CategoryResponse searchCategoryFull(int pageNo, int pageSize, String sortBy, String sortBy2, String keywords);

	Category getCategory(Long categoryId);
}
