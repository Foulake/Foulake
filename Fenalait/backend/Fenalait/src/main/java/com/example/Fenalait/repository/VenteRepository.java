package com.example.Fenalait.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.Vente;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long>{
	Optional<Vente> findById(Long idVente);

	@Query( "SELECT v FROM Vente v WHERE "
			+ "CONCAT(v.id, '', v.quantite, '', v.montant, '', v.remise, '', v.date)"
			+ " LIKE %?1%")
	public Page<Vente> findAllVente(String keyword, Pageable pageable);

	List<Vente> findByClientId(Long clientId);

	List<Vente> findByProduitId(Long produitId);



}
