package org.eclipse.rest;

import java.util.List;

import org.eclipse.dto.VoitureDto;
import org.eclipse.services.PanierService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/panier")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Panier", description = "Gestion du panier d’achat (session utilisateur)")
public class PanierResource {

	private PanierService panierService = new PanierService();

	@Context
	private HttpSession session;

	@SuppressWarnings("unchecked")
	private List<VoitureDto> getSessionPanier() {
		List<VoitureDto> panier = (List<VoitureDto>) session
				.getAttribute("panier");
		if (panier == null) {
			panier = panierService.getPanier(null);
			session.setAttribute("panier", panier);
		}
		return panier;
	}

	// --- GET : Récupération du panier complet ---
	@GET
	@Operation(summary = "Récupérer le contenu du panier", description = "Retourne la liste des voitures dans le panier actuel ainsi que le total.", responses = {
			@ApiResponse(responseCode = "200", description = "Panier récupéré avec succès", content = @Content(schema = @Schema(implementation = PanierResponse.class))),
			@ApiResponse(responseCode = "500", description = "Erreur interne du serveur")})
	public Response getPanier() {
		List<VoitureDto> panier = getSessionPanier();
		double total = panierService.calculerTotal(panier);
		return Response.ok(new PanierResponse(panier, total)).build();
	}

	// --- POST : Ajouter une voiture au panier ---
	@POST
	@Path("/add/{id}")
	@Operation(summary = "Ajouter une voiture au panier", description = "Ajoute une voiture au panier de l’utilisateur en session.", parameters = {
			@Parameter(name = "id", description = "ID de la voiture à ajouter", required = true)}, responses = {
					@ApiResponse(responseCode = "200", description = "Voiture ajoutée au panier", content = @Content(array = @ArraySchema(schema = @Schema(implementation = VoitureDto.class)))),
					@ApiResponse(responseCode = "404", description = "Voiture non trouvée"),
					@ApiResponse(responseCode = "500", description = "Erreur interne du serveur")})
	public Response addVoiture(@PathParam("id") int id) {
		List<VoitureDto> panier = getSessionPanier();
		panierService.addVoiture(panier, id);
		session.setAttribute("panier", panier);
		return Response.ok(panier).build();
	}

	// --- DELETE : Supprimer une voiture du panier ---
	@DELETE
	@Path("/remove/{id}")
	@Operation(summary = "Supprimer une voiture du panier", description = "Supprime une voiture spécifique du panier selon son ID.", parameters = {
			@Parameter(name = "id", description = "ID de la voiture à supprimer", required = true)}, responses = {
					@ApiResponse(responseCode = "200", description = "Voiture supprimée du panier", content = @Content(array = @ArraySchema(schema = @Schema(implementation = VoitureDto.class)))),
					@ApiResponse(responseCode = "404", description = "Voiture non trouvée dans le panier"),
					@ApiResponse(responseCode = "500", description = "Erreur interne du serveur")})
	public Response removeVoiture(@PathParam("id") int id) {
		List<VoitureDto> panier = getSessionPanier();
		panierService.removeVoiture(panier, id);
		session.setAttribute("panier", panier);
		return Response.ok(panier).build();
	}

	// --- DELETE : Vider le panier ---
	@DELETE
	@Path("/clear")
	@Operation(summary = "Vider le panier", description = "Supprime toutes les voitures du panier en cours.", responses = {
			@ApiResponse(responseCode = "200", description = "Panier vidé avec succès"),
			@ApiResponse(responseCode = "500", description = "Erreur interne du serveur")})
	public Response clearPanier() {
		List<VoitureDto> panier = getSessionPanier();
		panierService.clearPanier(panier);
		session.setAttribute("panier", panier);
		return Response.ok().build();
	}

	// --- DTO pour la réponse du panier ---
	@Schema(name = "PanierResponse", description = "Structure de réponse du panier avec total et liste de voitures")
	public static class PanierResponse {
		@ArraySchema(schema = @Schema(implementation = VoitureDto.class))
		public List<VoitureDto> voitures;

		@Schema(description = "Montant total du panier")
		public double total;

		public PanierResponse(List<VoitureDto> voitures, double total) {
			this.voitures = voitures;
			this.total = total;
		}
	}
}
