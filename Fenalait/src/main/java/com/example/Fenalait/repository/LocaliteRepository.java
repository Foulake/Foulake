package com.example.Fenalait.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.Localite;

@Repository
public interface LocaliteRepository extends JpaRepository<Localite, Long>{
	
	boolean existsLocalitesByNom(String nom);
	Page<Localite> findAll(Pageable pageable);

//	@Query("SELECT new com.gestion.api.dto.LocaliteResponseDto( l.nom, m.nomMagasin) FROM Localite l JOIN l.magasins m")
//	public List<LocaliteResponse> getLocalitesByMagasin();
	
//	@Query("SELECT lo FROM Localite lo WHERE lo.nom like %?1%"
//			+"or lo.description like %?1%")
//	public List<Localite> findByNom(String nom);

	
//	@Query("SELECT new com.gestion.api.dto.LocaliteResponseDto( l.id, l.nom, c.nomChamp) from Localite l Join l.champs c")
//	List<LocaliteResponse> getLocalitesByChamp();
	
	@Query("Select l from Localite l Where l.nom like %?1%"
			+"or l.description like %?1%"
			+"or l.id like %?1%")
	Page<Localite> findAll(Pageable pageable, String keyword);

}
