package com.cpg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(unique=true,nullable=false)
	@Email
	private String username;
	private String password;
	private String authorities;
	private boolean enabled;
	private String nom;
	
	public Utilisateur() {
		
	}

	

	
	
	
	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}






	public String getNom() {
		return nom;
	}






	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
