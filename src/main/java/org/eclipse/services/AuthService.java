package org.eclipse.services;

import org.eclipse.models.Utilisateur;
import org.eclipse.repositories.UtilisateurDao;
import org.eclipse.rest.JwtUtil;

public class AuthService {
	private final UtilisateurDao utilisateurDao = new UtilisateurDao();

	public Utilisateur register(String nom, String prenom, String email,
			String password) {
		// Vérifie si l'email existe déjà
		if (utilisateurDao.findByEmail(email) != null) {
			return null;
		}

		// Génération automatique du compte unique
		String compte = generateCompte(nom, prenom);

		Utilisateur u = new Utilisateur(nom, prenom, compte, email, password, 2,
				null);
		return utilisateurDao.save(u);
	}

	public Utilisateur login(String email, String password) {
		return utilisateurDao.findByEmailAndPassword(email, password);
	}

	public String generateToken(Utilisateur user) {
		return JwtUtil.generateToken(user.getEmail(),
				String.valueOf(user.getRole()));
	}

	private String generateCompte(String nom, String prenom) {
		return (prenom.charAt(0) + nom).toLowerCase()
				+ System.currentTimeMillis();
	}

}