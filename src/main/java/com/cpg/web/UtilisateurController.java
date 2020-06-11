package com.cpg.web;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cpg.dao.UtilisateurRepository;
import com.cpg.entities.Utilisateur;
import com.cpg.metier.UtilisateurService;



@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	private String social (Model model ) {
		
		return "login";
	}
	
	/*@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password) {
		return "index";
    
}*/

	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	private String all (Model model) {
	
		model.addAttribute("users",utilisateurService.tab());
		return "social";
	}
	
	@RequestMapping(value="/updates",method=RequestMethod.GET)
	private String Afficher (Model model, @RequestParam(name="id",defaultValue="0")int id) {
		List<Utilisateur> users =utilisateurService.tab();
		/*int i =users.size();
		List<Utilisateur> tab=users;
		for (int x=1;x<i;x++) { tab.add(users.get(x));}*/
		model.addAttribute("users",users);
		model.addAttribute("id",id);
		model.addAttribute("user", utilisateurService.find(id));
		model.addAttribute("u", new Utilisateur());
		return "updates";
	}
	
	@RequestMapping(value="/promos",method=RequestMethod.GET)
	private String promos (Model model ) {
		model.addAttribute("id", 0);
		return "promos";
	}
	
	@RequestMapping(value="/deleteU",method=RequestMethod.GET)
	private String delete (  int id) {
		utilisateurService.supprimer(id);
		return "confirmation";
	}
	
	@RequestMapping(value="/findU{nom}",method=RequestMethod.GET)
	private String find ( Model model, String nom) {
		//return utilisateurService.find(id);
		model.addAttribute("user", new Utilisateur());
		model.addAttribute("u", new Utilisateur());
		model.addAttribute("users", utilisateurService.findByNom(nom));
		return "updates";
	}
	
	@RequestMapping(value="/editU",method=RequestMethod.POST)
	private String FrmModifier (Model model , Utilisateur user,int id) {
		utilisateurService.modifier(user, id);
		model.addAttribute("user",utilisateurService.find(id));
		return "confirmation";
	}
	

	@RequestMapping(value="/403",method=RequestMethod.GET)
	private String denied () {
		
		return "403";
	}
	
	@RequestMapping(value="/confirm",method=RequestMethod.GET)
	private String confirm () {
		
		return "confirmation";
	}

	@RequestMapping(value="/",method=RequestMethod.GET)
	private String home () {
		
		return "redirect:/login";
	}
	
}
