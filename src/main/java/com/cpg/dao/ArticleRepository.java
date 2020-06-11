package com.cpg.dao;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cpg.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	@Query(value="Select * from article where magasin_id= ?",	
			countQuery="Select count(*) from article where magasin_id= ? ",
nativeQuery=true)
	public Page<Article> AfficherArticleSelonMagasin(int magasin_id,Pageable pageable);
	
	@Query(value="Select * from article where categorie_id= ?",
			countQuery="Select count(*) from article where categorie_id= ?",
			nativeQuery=true)
	public Page<Article> AfficherArticleSelonCategorie(int categorie_id,Pageable pageable);
	
	@Query(value="Select * from article where magasin_id= ? AND categorie_id= ?",
			countQuery="Select count(*) from article where magasin_id= ? AND categorie_id= ?"
			,nativeQuery=true)
	public Page<Article> AfficherArticleSelonMagasinAndCategorie(int magasin_id, int categorie_id,Pageable pageable);

}
