package com.cpg.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpg.dao.ArticleRepository;
import com.cpg.entities.Article;


@Service
@Transactional
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public List<Article> afficher (){
		return articleRepository.findAll();
	}
	public Article ajouter (Article article) {
		
		return articleRepository.save(article);
	}
	public void supprimer (int id) {
	articleRepository.deleteById(id);}
	
	public Article modifier (Article article,int stockcode) {
		
		articleRepository.deleteById(stockcode);
		return articleRepository.save(article);
	}
	
	public Article find (int id) {
		return articleRepository.getOne(id);
	}
	
	public Page<Article> findAllByMagasin(int magasin_id,PageRequest p,int page,int size){
		return articleRepository.AfficherArticleSelonMagasin(magasin_id,new PageRequest(page, size));
	}
	
	public Page<Article> findAll (int magasin_id,int categorie_id,PageRequest p,int page,int size){
		Page<Article> liste =null;
		if (magasin_id>0 && categorie_id>0) {
			liste= articleRepository.AfficherArticleSelonMagasinAndCategorie(magasin_id, categorie_id,new PageRequest(page, size));
		} else
		
		{ liste=articleRepository.AfficherArticleSelonMagasin(magasin_id,new PageRequest(page, size)); }
		return liste;
	}

}
