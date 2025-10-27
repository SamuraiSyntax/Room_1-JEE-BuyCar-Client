package org.eclipse.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.models.Voiture;
import org.eclipse.repositories.VoitureDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/panier")
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoitureDao voitureDao = new VoitureDao();

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupération du panier depuis la session
		List<Voiture> panier = (List<Voiture>) request.getSession().getAttribute("panier");

		if (panier == null) {
			panier = new ArrayList<>();
			request.getSession().setAttribute("panier", panier);
		}

		// Calcul du total
		double total = panier.stream().mapToDouble(Voiture::getPrix).sum();

		request.setAttribute("total", total);
		request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		int idVoiture = Integer.parseInt(request.getParameter("id"));

		List<Voiture> panier = (List<Voiture>) request.getSession().getAttribute("panier");
		if (panier == null) {
			panier = new ArrayList<>();
		}

		switch (action) {
		case "add":
			Voiture v = voitureDao.findById(idVoiture);
			if (v != null && !panier.contains(v)) {
				panier.add(v);
			}
			break;

		case "remove":
			panier.removeIf(voiture -> voiture.getIdVoiture() == idVoiture);
			break;

		case "clear":
			panier.clear();
			break;
		}

		// Met à jour la session
		request.getSession().setAttribute("panier", panier);

		// Redirige vers la page du panier
		response.sendRedirect(request.getContextPath() + "/panier");
	}
}