package org.eclipse.models;

import java.time.LocalDateTime;

public class Voiture {
	private int idVoiture;
	private String reference;
	private String marque;
	private String modele;
	private int annee;
	private String couleur;
	private double prix;
	private String description;
	private LocalDateTime dateAjout;
	private int idCategorie;

	public Voiture() {
	}

	public Voiture(int idVoiture, String reference, String marque, String modele, int annee, String couleur,
			double prix, String description, LocalDateTime dateAjout, int idCategorie) {
		this.idVoiture = idVoiture;
		this.reference = reference;
		this.marque = marque;
		this.modele = modele;
		this.annee = annee;
		this.couleur = couleur;
		this.prix = prix;
		this.description = description;
		this.dateAjout = dateAjout;
		this.idCategorie = idCategorie;
	}

	public Voiture(String reference, String marque, String modele, int annee, String couleur, double prix,
			String description, int idCategorie) {
		this(0, reference, marque, modele, annee, couleur, prix, description, null, idCategorie);
	}

	public int getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(int idVoiture) {
		this.idVoiture = idVoiture;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(LocalDateTime dateAjout) {
		this.dateAjout = dateAjout;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	@Override
	public String toString() {
		return marque + " " + modele + " (" + annee + ")";
	}
}
