package com.example.Fenalait.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.CategorieFournisseur;

@Repository
public interface CategorieFournisseurRepository extends JpaRepository<CategorieFournisseur, Long>{
	
	@Query("Select p from CategorieFournisseur p Where p.description like %?1%"
			+"or p.id like %?1%")
	public Page<CategorieFournisseur> findAll(Pageable pageable, String keyword);
}
	

