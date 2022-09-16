package com.example.Fenalait.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.Employe;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long>{
	
	@Query("SELECT em FROM Employe em WHERE "
			+ "CONCAT(em.id, '', em.firstName, '', em.lastName, '', em.email, '', em.telEmploye)" 
			+ " LIKE %?1%")
	Page<Employe> findAll(String keyword, Pageable pageable);

	Page<Employe> findAll(Pageable pageable, String keywords);

}
