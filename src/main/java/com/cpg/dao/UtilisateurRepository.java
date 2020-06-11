package com.cpg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cpg.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	@Query(value="Select * from utilisateur where nom = ?",nativeQuery=true)
	public Utilisateur findByNom (String nom);
}
