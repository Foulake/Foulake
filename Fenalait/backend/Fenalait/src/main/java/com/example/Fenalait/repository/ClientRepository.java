package com.example.Fenalait.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Optional<Client> findById(Long id);
	//faire une recherche
	//@Query(value= "Select* from client where "
		//	+"match(prenom_Client, nom_Client)"
		//	+"against(?1)",
		//	nativeQuery = true)
	//public List<Client> findAll(String keyword);
	
	@Query("SELECT c FROM Client c WHERE "
			+ "CONCAT(c.id, '', c.nomClient, '', c.telClient, '', c.prenomClient)" 
			+ " LIKE %?1%")
	Page<Client> findAll(Pageable pageable, String keywords);

	
	public Page<Client> findAll(Pageable pageable);

	boolean existsClientByTelClient(String telClient);

	

}
