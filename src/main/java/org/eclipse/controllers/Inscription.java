package org.eclipse.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.eclipse.models.Utilisateur;
import org.eclipse.repositories.UtilisateurDao;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UtilisateurDao utilisateurDao;

	@Override
	public void init() throws ServletException {
		utilisateurDao = new UtilisateurDao();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");

		if (nom == null || prenom == null || email == null || motDePasse == null || nom.isEmpty() || prenom.isEmpty()
				|| email.isEmpty() || motDePasse.isEmpty()) {

			request.setAttribute("erreur", "Tous les champs sont obligatoires.");

			request.getRequestDispatcher("/").forward(request, response);

			return;
		}
		try {
			// Création de l'utilisateur
			Utilisateur utilisateur = new Utilisateur(nom, prenom, email, motDePasse);

			Utilisateur savedUser = utilisateurDao.save(utilisateur);

			if (savedUser != null) {
				// Succès → redirection vers la page de connexion
				response.sendRedirect(request.getContextPath() + "/");
			} else {
				// Échec d’insertion → retour au formulaire
				request.setAttribute("erreur", "Une erreur est survenue lors de l’inscription.");
				request.getRequestDispatcher("/").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erreur", "Erreur serveur : " + e.getMessage());
			request.getRequestDispatcher("/").forward(request, response);
		}
	}
}
