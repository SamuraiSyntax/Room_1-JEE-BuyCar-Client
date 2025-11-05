package org.eclipse.rest;

import org.eclipse.dto.ErrorDto;
import org.eclipse.dto.VoitureDto;
import org.eclipse.service.VoitureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/voitures")
@Tag(name = "Voitures", description = "API de gestion des voitures")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VoitureResource {

 private final VoitureService service = new VoitureService();

 @GET
 @Operation(summary = "Lister les voitures", description = "Retourne la liste des voitures avec options de filtrage, tri et pagination.")
 @ApiResponses(value = {
   @ApiResponse(responseCode = "200", description = "Liste des voitures récupérée avec succès", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VoitureDto.class)))),
   @ApiResponse(responseCode = "400", description = "Paramètres de requête invalides", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
 public Response findAll(@Context UriInfo uriInfo,
   @QueryParam("marque") String marque,
   @QueryParam("modele") String modele,
   @QueryParam("page") @DefaultValue("0") int page,
   @QueryParam("size") @DefaultValue("5") int size,
   @QueryParam("sort") String sort) {

  if (page < 0 || size <= 0) {
   return Response.status(Response.Status.BAD_REQUEST).entity(
     new ErrorDto(400, "Pagination invalide", uriInfo.getPath()))
     .build();
  }

  return Response.ok(service.findAll()).build();
 }

 @GET
 @Path("{id}")
 @Operation(summary = "Trouver une voiture par ID", description = "Retourne les détails d’une voiture spécifique à partir de son identifiant.")
 @ApiResponses(value = {
   @ApiResponse(responseCode = "200", description = "Voiture trouvée", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VoitureDto.class))),
   @ApiResponse(responseCode = "404", description = "Voiture introuvable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
 public Response findById(@PathParam("id") int id,
   @Context UriInfo uriInfo) {

  VoitureDto dto = service.findById(id);

  if (dto == null) {
   return Response.status(Response.Status.NOT_FOUND).entity(
     new ErrorDto(404, "Voiture introuvable", uriInfo.getPath()))
     .build();
  }

  return Response.ok(dto).build();
 }

 @POST
 @Operation(summary = "Créer une nouvelle voiture", description = "Ajoute une nouvelle voiture et retourne l’objet créé avec son URI.")
 @ApiResponses(value = {
   @ApiResponse(responseCode = "201", description = "Voiture créée avec succès", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VoitureDto.class))),
   @ApiResponse(responseCode = "400", description = "Données invalides", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
 public Response create(VoitureDto dto, @Context UriInfo uriInfo) {
  VoitureDto created = service.create(dto);
  UriBuilder builder = uriInfo.getAbsolutePathBuilder();
  builder.path(String.valueOf(created.getIdVoiture()));
  return Response.created(builder.build()).entity(created).build();
 }

 @PUT
 @Path("{id}")
 @Operation(summary = "Mettre à jour une voiture", description = "Modifie les informations d’une voiture existante identifiée par son ID.")
 @ApiResponses(value = {
   @ApiResponse(responseCode = "200", description = "Voiture mise à jour avec succès", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VoitureDto.class))),
   @ApiResponse(responseCode = "404", description = "Voiture introuvable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))),
   @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
 public Response update(@PathParam("id") int id, VoitureDto dto,
   @Context UriInfo uriInfo) {

  VoitureDto updated = service.update(id, dto);

  if (updated == null) {
   return Response.status(Response.Status.NOT_FOUND).entity(
     new ErrorDto(404, "Voiture introuvable", uriInfo.getPath()))
     .build();
  }

  return Response.ok(updated).build();
 }

 @DELETE
 @Path("{id}")
 @Operation(summary = "Supprimer une voiture", description = "Supprime la voiture identifiée par son ID.")
 @ApiResponses(value = {
   @ApiResponse(responseCode = "204", description = "Voiture supprimée avec succès"),
   @ApiResponse(responseCode = "404", description = "Voiture introuvable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)))})
 public Response delete(@PathParam("id") int id, @Context UriInfo uriInfo) {

     boolean deleted = service.delete(id);

     if (!deleted) {
         return Response.status(Response.Status.NOT_FOUND)
                 .entity(new ErrorDto(404, "Voiture introuvable", uriInfo.getPath()))
                 .build();
     }

     // Cas succès → 204 No Content (standard REST)
     return Response.noContent().build();
 }
}

  