package org.eclipse.dto;

public class VoitureDto {
	private int idVoiture;
	private int annee;
	private String couleur;
	private String description;
	private int idCategorie;
	private String marque;
	private String modele;
	private double prix;
	private String reference;

	public VoitureDto() {
	}

	public VoitureDto(int idVoiture, String reference, String marque, String modele, int annee, String couleur,
			double prix, String description, int idCategorie) {
		this.idVoiture = idVoiture;
		this.reference = reference;
		this.marque = marque;
		this.modele = modele;
		this.annee = annee;
		this.couleur = couleur;
		this.prix = prix;
		this.description = description;
		this.idCategorie = idCategorie;
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
