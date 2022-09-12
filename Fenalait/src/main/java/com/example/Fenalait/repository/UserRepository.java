package com.example.Fenalait.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Fenalait.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	boolean existsUsersByEmail(String email);
	
//	@Query("SELECT new com.gestion.api.dto.UserResponseDto(u.id, u.nom, u.email, p.name) FROM User u JOIN u.products p")
//	public List<UserResponseDto> getUsersByProduct();
	
	@Query("Select p from User p Where p.nom like %?1%"
			+"or p.prenom like %?1%"
			+"or p.id like %?1%"
			+"or p.email like %?1%")
	public List<User> findAll(String nom);
	
	@Query("Select p from User p Where p.nom like %?1%"
			+"or p.prenom like %?1%"
			+"or p.id like %?1%"
			+"or p.email like %?1%")
	public Page<User> findAll(Pageable pageable, String keyword);
	
	//@Query("SELECT new com.gestion.api.dto.UserResponseDto(u.id, u.nom, u.email, v.nom) FROM User u JOIN u.vanes v")
	//List<UserResponseDto> getUsersByVane();
	
	
}
