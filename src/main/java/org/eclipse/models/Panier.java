package org.eclipse.models;

import java.time.LocalDateTime;

public class Panier {

	private int id_panier;
	private LocalDateTime date_creation;
	private int id_voiture;
	private int id_user;

	public Panier(int id_panier, LocalDateTime date_creation, int id_voiture, int id_user) {
		super();
		this.id_panier = id_panier;
		this.date_creation = date_creation;
		this.id_voiture = id_voiture;
		this.id_user = id_user;
	}

	public int getId_panier() {
		return id_panier;
	}

	public void setId_panier(int id_panier) {
		this.id_panier = id_panier;
	}

	public LocalDateTime getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(LocalDateTime date_creation) {
		this.date_creation = date_creation;
	}

	public int getId_voiture() {
		return id_voiture;
	}

	public void setId_voiture(int id_voiture) {
		this.id_voiture = id_voiture;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

}