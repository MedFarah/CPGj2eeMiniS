package com.cpg.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
@Entity
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@NotNull
	
	private int stockcode;
	@NotEmpty
	@Length(min=4,max=30)
	private String designation;
	
	@Length(min=4,max=30)
	private String reference;
	@NotNull(message="Ne peut pas etre vide !!")
	@DecimalMin(value = "0.1", inclusive = true)
	private float quantite;
	@NotNull
	@DecimalMin(value = "0.1", inclusive = true)
	private float prix;
	@ManyToOne
	private Magasin magasin;
	@ManyToOne
	private Categorie categorie;
	
	public Article() {
	
	}

	public int getStockcode() {
		return stockcode;
	}

	public void setStockcode(int stockcode) {
		this.stockcode = stockcode;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	

	public float getQuantite() {
		return quantite;
	}

	public void setQuantite(float quantite) {
		this.quantite = quantite;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	

}
