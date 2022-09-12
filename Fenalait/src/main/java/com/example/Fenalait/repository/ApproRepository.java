package com.example.Fenalait.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.Approvissionnement;


@Repository
public interface ApproRepository extends JpaRepository<Approvissionnement, Long> {
	Optional<Approvissionnement> findByIdAppro(Long idAppro);

	@Query( "SELECT appro FROM Approvissionnement appro WHERE "
			+ "CONCAT(appro.idAppro, '', appro.qteAppro, '', appro.dateAppro)"
			+ " LIKE %?1%")
	public Page<Approvissionnement> findAllAppro(String keyword, Pageable pageable);


}
