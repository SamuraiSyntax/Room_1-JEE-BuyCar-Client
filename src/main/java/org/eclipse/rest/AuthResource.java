package org.eclipse.rest;

import org.eclipse.dto.UtilisateurDto;
import org.eclipse.models.Utilisateur;
import org.eclipse.service.AuthService;

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

	public static class RegisterRequest {
		public String nom;
	    public String prenom;
	    public String email;
	    public String password;
	}

	public static class LoginRequest {
	    public String email;
	    public String password;
	}
	public static class AuthResponse {
        public String token;
        public UtilisateurDto user;
        public AuthResponse(String token, UtilisateurDto user) {
            this.token = token;
            this.user = user;
        }
    }
	
	
	@POST
    @Path("/register")
    public Response register(RegisterRequest req) {
        if (req == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Corps invalide\"}").build();
        }

        Utilisateur user = authService.register(req.nom, req.prenom, req.email, req.password);
        if (user == null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\":\"Email déjà utilisé\"}").build();
        }

        String token = authService.generateToken(user);
        return Response.status(Response.Status.CREATED)
                .entity(new AuthResponse(token, UtilisateurDto.toDto(user)))
                .build();
    }
	
	@POST
    @Path("/login")
    public Response login(LoginRequest req) {
        if (req == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Corps invalide\"}").build();
        }

        Utilisateur user = authService.login(req.email, req.password);
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"Email ou mot de passe incorrect\"}").build();
        }

        String token = authService.generateToken(user);
        return Response.ok(new AuthResponse(token, UtilisateurDto.toDto(user))).build();
    }

}
