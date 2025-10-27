package org.eclipse.models;

public class Utilisateur {
	private int id;
	private String nom;
	private String prenom;
	private String compte;
	private String email;
	private String password;
	private int role;
	private String telephone;

	public Utilisateur(String nom, String prenom, String email, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}

	public Utilisateur(int id, String nom, String prenom, String email, String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}

	public Utilisateur(int id, String nom, String prenom, String compte, String email, String password, int role,
			String telephone) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.compte = compte;
		this.email = email;
		this.password = password;
		this.role = role;
		this.telephone = telephone;
	}

	public Utilisateur(String nom, String prenom, String compte, String email, String password, int role,
			String telephone) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.compte = compte;
		this.email = email;
		this.password = password;
		this.role = role;
		this.telephone = telephone;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", compte=" + compte + ", email="
				+ email + ", password=" + password + ", role=" + role + ", telephone=" + telephone + "]";
	}

}
