package com.cpg.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpg.dao.UtilisateurRepository;
import com.cpg.entities.Utilisateur;

@Service
public class UtilisateurService {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public List<Utilisateur> tab(){
		return utilisateurRepository.findAll();
	}

	public void supprimer (int id) {
		utilisateurRepository.deleteById(id);}
		
		public Utilisateur modifier (Utilisateur user,int id) {
			
			//utilisateurRepository.deleteById(id);
			return utilisateurRepository.save(user);
		}
		public Utilisateur find (int id) {
			return utilisateurRepository.getOne(id);
		}
		
		public Utilisateur findByNom (String ch) {
			return utilisateurRepository.findByNom(ch);
		}
		
}
