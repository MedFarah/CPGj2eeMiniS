package com.cpg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpg.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

}
