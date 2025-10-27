package org.eclipse.controllers;

import java.io.IOException;

import org.eclipse.models.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utilisateur u = (Utilisateur) request.getSession().getAttribute("utilisateur");

		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}
}