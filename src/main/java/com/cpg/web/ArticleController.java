package com.cpg.web;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cpg.dao.ArticleRepository;
import com.cpg.dao.CategorieRepository;
import com.cpg.dao.MagasinRepository;
import com.cpg.entities.Article;
import com.cpg.entities.Magasin;
import com.cpg.metier.ArticleService;
import com.cpg.metier.MailService;

@Controller

public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	
	
	private ArticleRepository articleRepository;
	private MagasinRepository magasinRepository;
	private CategorieRepository categorieRepository;
	
	
	
	@Autowired
	public ArticleController(ArticleRepository articleRepository,MagasinRepository magasinRepository,CategorieRepository categorieRepository) {
		
		this.articleRepository = articleRepository;
		this.magasinRepository=magasinRepository;
		this.categorieRepository=categorieRepository;
	}

	@RequestMapping(value="/index",method=RequestMethod.GET)
	private String Afficher (Model model , @RequestParam(name="page",defaultValue="0") int page , @RequestParam(name="size",defaultValue="8") int size,HttpServletRequest request ) {
		Page<Article> articles =articleRepository.findAll(new PageRequest(page, size));
		model.addAttribute("articles",articles.getContent());
		int[] pages = new int[articles.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", size);
		model.addAttribute("pageActive", page);
		model.addAttribute("nom", request.getUserPrincipal().getName());
		
		return "index";
		
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	private String addmdl (Model model ) {
		//List<Article> articles =articleService.afficher();
		model.addAttribute("article",new Article());
		model.addAttribute("magasins", magasinRepository.findAll());
		model.addAttribute("categories", categorieRepository.findAll());
		return "add";
	}
	
	@RequestMapping(value="/findOne",method=RequestMethod.GET)
	private String chercher (Model model,int stockcode ) {
		//List<Article> articles =articleService.afficher();
		model.addAttribute("articles",articleService.find(stockcode));
		return "index";
	}
	
	@RequestMapping(value="/supp",method=RequestMethod.GET)
	private String delet (Model model , @RequestParam(name="page",defaultValue="0") int page , @RequestParam(name="size",defaultValue="7") int size) {
		Page<Article> articles =articleRepository.findAll(new PageRequest(page, size));
		model.addAttribute("articles",articles.getContent());
		int[] pages = new int[articles.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", size);
		model.addAttribute("pageActive", page);
		return "delete";
	}
	@RequestMapping(value="/find",method=RequestMethod.GET)
    private String afficherSelonMagasin(Model model,@RequestParam(defaultValue="0") int magasin_id ,@RequestParam(defaultValue="0") int categorie_id,  @RequestParam(name="page",defaultValue="0") int page , @RequestParam(name="size",defaultValue="4") int size) {
		Page<Article> articles = articleService.findAll(magasin_id,categorie_id,new PageRequest(page, size),page,size);
		if(magasin_id>0) {
		Magasin m = magasinRepository.getOne(magasin_id);
		model.addAttribute("nomMag",m.getNom());}
		
		//Page<Article> articles = new PageImpl<>(articleService.findAllByMagasin(magasin_id));
		model.addAttribute("articles",articles.getContent() );
		
	int	pages = articles.getTotalPages();
	int[] pageT = new int[pages]; for (int i=0;i<pages;i++) pageT[i]=i;
		model.addAttribute("pagess", pageT);
		model.addAttribute("size", size);
		model.addAttribute("pageActive", page);
		model.addAttribute("id", magasin_id);
		model.addAttribute("idc", categorie_id);
		return "promos";
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	private String save (Model model, @Valid Article a , BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			{		model.addAttribute("magasins", magasinRepository.findAll());
			model.addAttribute("categories", categorieRepository.findAll());
			return "add";}
		model.addAttribute("e", articleService.ajouter(a)) ;
		return "confirmation";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	private String FrmModifier (Model model , int stockcode) {
		Article a = articleService.find(stockcode);
		model.addAttribute("article",articleService.find(stockcode));
		model.addAttribute("magasins", magasinRepository.findAll());
		model.addAttribute("categories", categorieRepository.findAll());
		model.addAttribute("cat", a.getCategorie().getId());
		model.addAttribute("mag", a.getMagasin().getId());
		return "update";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	private String update ( int stockcode, @Valid Article a ,  BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("magasins", magasinRepository.findAll());
			model.addAttribute("categories", categorieRepository.findAll());
			model.addAttribute("cat", a.getCategorie().getId());
			model.addAttribute("mag", a.getMagasin().getId());
			return "update";
		}
			
		articleService.modifier(a,stockcode);
		return "confirmation";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	private String delete ( Model model, int stockcode) {
		articleService.supprimer(stockcode);
		return "confirmation";
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	private String ad (Model model ) {
		model.addAttribute("e",new Article());
		return "jdid";
	}
	
	
	
	
	@RequestMapping(value="/class",method=RequestMethod.GET)
	private String categorie () {
		
		return "categorie";
	}
	@RequestMapping(value="/test",method=RequestMethod.GET)
	private String test () {
		
		return "Ajouter";
	}
	

	
}
