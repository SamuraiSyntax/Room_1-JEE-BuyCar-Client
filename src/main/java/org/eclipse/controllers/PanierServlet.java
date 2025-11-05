package org.eclipse.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.dto.VoitureDto;
import org.eclipse.services.PanierService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/panier")
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PanierService panierService = new PanierService();

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<VoitureDto> panier = (List<VoitureDto>) request.getSession()
				.getAttribute("panier");
		if (panier == null) {
			panier = new ArrayList<>();
			request.getSession().setAttribute("panier", panier);
		}

		double total = panierService.calculerTotal(panier);
		request.setAttribute("total", total);
		request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request,
				response);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String idStr = request.getParameter("id");

		List<VoitureDto> panier = (List<VoitureDto>) request.getSession()
				.getAttribute("panier");
		if (panier == null) {
			panier = new ArrayList<>();
		}

		if (action != null) {
			switch (action) {
				case "add" -> {
					try {
						int id = Integer.parseInt(idStr);
						panierService.addVoiture(panier, id);
					} catch (NumberFormatException e) {
						System.err.println("⚠️ ID voiture invalide : " + idStr);
					}
				}
				case "remove" -> {
					try {
						int id = Integer.parseInt(idStr);
						panierService.removeVoiture(panier, id);
					} catch (NumberFormatException e) {
						System.err.println("⚠️ ID voiture invalide : " + idStr);
					}
				}
				case "clear" -> panierService.clearPanier(panier);
			}
		}

		request.getSession().setAttribute("panier", panier);
		response.sendRedirect(request.getContextPath() + "/panier");
	}
}
