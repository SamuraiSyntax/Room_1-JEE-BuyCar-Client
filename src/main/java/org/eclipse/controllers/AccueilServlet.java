package org.eclipse.controllers;

import java.io.IOException;
import java.util.List;

import org.eclipse.dto.VoitureDto;
import org.eclipse.models.Utilisateur;
import org.eclipse.services.VoitureService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Utilisateur u = (Utilisateur) request.getSession()
				.getAttribute("utilisateur");
		request.setAttribute("utilisateur", u);

		String marque = request.getParameter("marque");
		String modele = request.getParameter("modele");
		String prixMinStr = request.getParameter("prixMin");
		String prixMaxStr = request.getParameter("prixMax");

		Double prixMin = null;
		Double prixMax = null;

		try {
			if (prixMinStr != null && !prixMinStr.isBlank())
				prixMin = Double.parseDouble(prixMinStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		try {
			if (prixMaxStr != null && !prixMaxStr.isBlank())
				prixMax = Double.parseDouble(prixMaxStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		VoitureService voitureService = new VoitureService();
		List<VoitureDto> voitures = voitureService.search(marque, modele,
				prixMin, prixMax, null);

		request.setAttribute("voitures", voitures);
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request,
				response);
	}
}