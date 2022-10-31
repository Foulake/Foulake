package com.example.Fenalait.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.Magasin;

@Repository
public interface MagasinRepository extends CrudRepository<Magasin, Long>{
	
	boolean existsByNomMagasin(String nomMagasin);
	Page<Magasin> findAll(Pageable pageable);
	
//	@Query("SELECT new com.gestion.api.dto.MagasinResponseDto(m.id, m.nomMagasin, p.name) FROM Magasin m JOIN m.products p")
//	public List<MagasinResponse> getMagasinsByProduct();
	
//	@Query("SELECT m FROM Magasin m WHERE m.nomMagasin like %?1%")
//	public List<Magasin> findByNomMagasin(String nomMagasin);
	
	@Query("Select p from Magasin p Where p.nomMagasin like %?1%"
			+"or p.id like %?1%")
	public Page<Magasin> findAll(Pageable pageable, String keywords);
	
	List<Magasin> findByLocaliteId(Long localiteId);
}
