package org.eclipse.config;

import java.util.Set;

import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/openapi")
public class OpenApiResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Context Application app) {
		try {
			OpenAPI openAPI = new OpenAPI().openapi("3.1.0").info(new Info()
					.title("API Voitures - BuyCar").version("1.0.0")
					.description(
							"Documentation OpenAPI pour l’API Voitures du projet BuyCar."));

			SwaggerConfiguration cfg = new SwaggerConfiguration()
					.openAPI(openAPI)
					.resourcePackages(Set.of("org.eclipse.rest"))
					.prettyPrint(true);

			var ctx = new JaxrsOpenApiContextBuilder().application(app)
					.openApiConfiguration(cfg).buildContext(true);

			OpenAPI result = ctx.read();
			String json = io.swagger.v3.core.util.Json.mapper()
					.writeValueAsString(result);

			return Response.ok(json, MediaType.APPLICATION_JSON).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(
					"{\"error\": \"Erreur lors de la génération du document OpenAPI.\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}
	}
}
