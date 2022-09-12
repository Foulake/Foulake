package com.example.Fenalait.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.Vente;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long>{
	Optional<Vente> findByIdVente(Long idVente);

	@Query( "SELECT v FROM Vente v WHERE "
			+ "CONCAT(v.idVente, '', v.quantite, '', v.montant, '', v.remise, '', v.date)"
			+ " LIKE %?1%")
	public Page<Vente> findAllVente(String keyword, Pageable pageable);



}
