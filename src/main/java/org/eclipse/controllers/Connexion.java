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
 * Servlet implementation class Connexion
 */
@WebServlet({ "/connexion", "/deconnexion" })
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
 UtilisateurDao utilisateurDao ;
	 
	 @Override
		public void init() throws ServletException {
		 utilisateurDao = new UtilisateurDao();
			super.init();
		}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getServletPath().contains("deconnexion")) {
			var session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/connexion");
			   return;			
		}
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
		
		Utilisateur utilisateur = utilisateurDao.findByEmailAndPassword(email, motDePasse);
		
		if (utilisateur != null) {
			var session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			session.setAttribute("prenom", utilisateur.getPrenom());
			
			//request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/accueil");
			
		}else {
            
            request.setAttribute("erreur", "Utilisateur introuvable. Vérifiez vos identifiants.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
	}
}
