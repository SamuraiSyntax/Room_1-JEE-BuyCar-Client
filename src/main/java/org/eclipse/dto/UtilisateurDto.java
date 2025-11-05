package org.eclipse.dto;

import org.eclipse.models.Utilisateur;

public class UtilisateurDto {
	private int id;
	private String nom;
	private String prenom;
	private String compte;
	private String email;
	private int role;
	private String telephone;

	public UtilisateurDto() {
	}

	public UtilisateurDto(int id, String nom, String prenom, String compte,
			String email, int role, String telephone) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.compte = compte;
		this.email = email;
		this.role = role;
		this.telephone = telephone;
	}

	// getters / setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCompte() {
		return compte;
	}
	public void setCompte(String compte) {
		this.compte = compte;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	// Conversion depuis l'entité Utilisateur
	public static UtilisateurDto toDto(Utilisateur u) {
		if (u == null)
			return null;
		return new UtilisateurDto(u.getId(), u.getNom(), u.getPrenom(),
				u.getCompte(), u.getEmail(), u.getRole(), u.getTelephone());
	}

	@Override
	public String toString() {
		return "UtilisateurDto [id=" + id + ", nom=" + nom + ", prenom="
				+ prenom + ", compte=" + compte + ", email=" + email + ", role="
				+ role + ", telephone=" + telephone + "]";
	}
}