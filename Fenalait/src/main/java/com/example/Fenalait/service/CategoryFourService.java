package com.example.Fenalait.service;

import org.springframework.stereotype.Service;

import com.example.Fenalait.dto.CategoryFourDto;
import com.example.Fenalait.dto.CategoryFourResponse;

@Service
public interface CategoryFourService {

	CategoryFourDto createCategoryFour(CategoryFourDto categoryFourDto);

    CategoryFourResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir);

    CategoryFourDto getCategoryFourById(Long id);

    CategoryFourDto updateCategoryFour(CategoryFourDto categoryFourDto, Long id);

    void deleteCategoryFourById(Long id);

	CategoryFourResponse searchCategoryFourFull(int pageNo, int pageSize, String sortBy, String sortDir, String keywords);

}
