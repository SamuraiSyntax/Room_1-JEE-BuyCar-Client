package org.eclipse.rest;

import org.eclipse.dto.UtilisateurDto;
import org.eclipse.models.Utilisateur;
import org.eclipse.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Tag(name = "Authentification", description = "API d'enregistrement et de connexion utilisateur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

	private final AuthService authService = new AuthService();

	// --- DTOs internes pour Swagger et transport JSON ---
	@Schema(name = "RegisterRequest", description = "Données nécessaires pour créer un nouvel utilisateur")
	public static class RegisterRequest {
		@Schema(example = "Dupont", description = "Nom de l'utilisateur")
		public String nom;

		@Schema(example = "Jean", description = "Prénom de l'utilisateur")
		public String prenom;

		@Schema(example = "jean.dupont@email.com", description = "Adresse email de l'utilisateur")
		public String email;

		@Schema(example = "password123", description = "Mot de passe de l'utilisateur")
		public String password;
	}

	@Schema(name = "LoginRequest", description = "Données de connexion d'un utilisateur existant")
	public static class LoginRequest {
		@Schema(example = "jean.dupont@email.com", description = "Adresse email de l'utilisateur")
		public String email;

		@Schema(example = "password123", description = "Mot de passe de l'utilisateur")
		public String password;
	}

	@Schema(name = "AuthResponse", description = "Réponse d'authentification contenant le token et les informations de l'utilisateur")
	public static class AuthResponse {
		@Schema(description = "Token JWT d'authentification", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
		public String token;

		@Schema(description = "Utilisateur authentifié")
		public UtilisateurDto user;

		public AuthResponse(String token, UtilisateurDto user) {
			this.token = token;
			this.user = user;
		}
	}

	// --- POST /register ---
	@POST
	@Path("/register")
	@Operation(summary = "Enregistrer un nouvel utilisateur", description = "Crée un nouvel utilisateur et retourne un token JWT pour l'authentification.", responses = {
			@ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès", content = @Content(schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "400", description = "Requête invalide (corps manquant ou incorrect)", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
			@ApiResponse(responseCode = "409", description = "Adresse email déjà utilisée", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
			@ApiResponse(responseCode = "500", description = "Erreur interne du serveur", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
	public Response register(RegisterRequest req) {
		if (req == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\":\"Corps invalide\"}").build();
		}

		Utilisateur user = authService.register(req.nom, req.prenom, req.email,
				req.password);
		if (user == null) {
			return Response.status(Response.Status.CONFLICT)
					.entity("{\"error\":\"Email déjà utilisé\"}").build();
		}

		String token = authService.generateToken(user);
		return Response.status(Response.Status.CREATED)
				.entity(new AuthResponse(token, UtilisateurDto.toDto(user)))
				.build();
	}

	// --- POST /login ---
	@POST
	@Path("/login")
	@Operation(summary = "Connexion d’un utilisateur existant", description = "Vérifie les identifiants et retourne un token JWT pour les requêtes protégées.", responses = {
			@ApiResponse(responseCode = "200", description = "Connexion réussie", content = @Content(schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "400", description = "Requête invalide (corps manquant ou incorrect)", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
			@ApiResponse(responseCode = "401", description = "Email ou mot de passe incorrect", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
			@ApiResponse(responseCode = "500", description = "Erreur interne du serveur", content = @Content(mediaType = MediaType.APPLICATION_JSON))})
	public Response login(LoginRequest req) {
		if (req == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\":\"Corps invalide\"}").build();
		}

		Utilisateur user = authService.login(req.email, req.password);
		if (user == null) {
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity("{\"error\":\"Email ou mot de passe incorrect\"}")
					.build();
		}

		String token = authService.generateToken(user);
		return Response.ok(new AuthResponse(token, UtilisateurDto.toDto(user)))
				.build();
	}
}
