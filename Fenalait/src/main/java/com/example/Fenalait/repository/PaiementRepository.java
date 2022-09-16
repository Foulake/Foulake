package com.example.Fenalait.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.Paiement;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long>{

	
	
	@Query("Select pt from Paiement pt Where pt.date like %?1%"
			+"or pt.id like %?1%"
			+"or pt.date like %?1%")
	public List<Paiement> findAll(String keyword);

	public List<Paiement> findByFournisseurId(Long fournisseurId);

	public Page<Paiement> findAll(Pageable pageable, String keywords);

}
