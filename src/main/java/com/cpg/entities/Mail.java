package com.cpg.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mail {
	@Id
	private String message;
	private String recipent;
	public String getMessage() {
		return message;
	}
	
	

	public Mail() {
		super();
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getRecipent() {
		return recipent;
	}



	public void setRecipent(String recipent) {
		this.recipent = recipent;
	}
}
