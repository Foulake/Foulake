package com.example.Fenalait.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.Category;
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	boolean existsCategorysByNom(String nom);
	Page<Category> findAll(Pageable pageable);

//	@Query("SELECT new com.gestion.api.dto.CategoryResponseDto(c.id , c.nom, p.name) FROM Category c JOIN c.products p")
//	public List<CategoryResponse> getCategorysByProduct();
	
	@Query("SELECT m FROM Category m WHERE m.nom like %?1%")
	public List<Category> findByNom(String nom);
	
	
	@Query("Select c from Category c Where c.nom like %?1%"
			+"or c.id like %?1%")
	Page<Category> findAll(Pageable pageable, String keyword);

}
