package com.example.Fenalait.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.CategorieFournisseur;

@Repository
public interface CategorieFournisseurRepository extends JpaRepository<CategorieFournisseur, Long>{
	
}
	

