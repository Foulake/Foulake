package com.example.Fenalait.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.Fournisseur;
import com.example.Fenalait.model.User;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>{
	Optional<Fournisseur> findById(Long id);
	


	@Query("SELECT f FROM Fournisseur f WHERE "
			+ "CONCAT( f.id, '', f.nom, '',  f.prenom, '', f.dateFour, '', f.tel )"
			+ " LIKE %?1%")
	

	Page<Fournisseur> findAll(Pageable pageable, String keyword);



	List<Fournisseur> findByCategorieFournisseurId(Long categoryFourId);
	

}
