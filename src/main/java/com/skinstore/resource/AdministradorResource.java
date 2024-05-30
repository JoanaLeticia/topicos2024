package com.skinstore.resource;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.skinstore.dto.AdministradorDTO;
import com.skinstore.form.ImageForm;
import com.skinstore.service.AdministradorFileServiceImpl;
import com.skinstore.service.AdministradorService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/admins")
public class AdministradorResource {
    
    @Inject
    public AdministradorService administradorService;

    @Inject
    public AdministradorFileServiceImpl fileService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(administradorService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(administradorService.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(administradorService.findByNome(nome)).build();
    }

    @POST
    @Transactional
    public Response create(AdministradorDTO admDTO) {
        return Response.status(Status.CREATED).entity(administradorService.create(admDTO)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, AdministradorDTO admDTO) {
        administradorService.update(id, admDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        administradorService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{id}/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        fileService.salvar(id, form.getNomeImagem(), form.getImagem());
        return Response.noContent().build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }   
}
