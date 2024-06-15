package com.skinstore.resource;

import org.jboss.logging.Logger;

import com.skinstore.application.Result;
import com.skinstore.dto.UsuarioDTO;
import com.skinstore.dto.UsuarioResponseDTO;
import com.skinstore.service.UsuarioService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    @Inject
    UsuarioService service;
    private static final Logger LOG = Logger.getLogger(UsuarioResource.class);

    @POST
    @RolesAllowed({ "Admin" })
    public Response insert(@Valid UsuarioDTO dto) {

        LOG.debug("Debug de inserção de usuarios.");
        try {
            LOG.info("Inserindo usuarios");
            UsuarioResponseDTO retorno = service.insert(dto);
            return Response.status(201).entity(retorno).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de usuarios.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response update(UsuarioDTO dto, @PathParam("id") Long id) {

        try {
            LOG.info("Atualizando usuarios");
            service.update(dto, id);
            return Response.noContent().build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug da atualização de usuarios.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {

        try {
            LOG.info("Deletando o usuarios");
            service.delete(id);
            return Response.noContent().build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug da exclusão do usuarios.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    //@RolesAllowed({ "Admin" })
    public Response findAll() {
        LOG.info("Buscando todos os usuarios.");
        LOG.debug("Debug de busca de lista de usuarios.");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        try {
            UsuarioResponseDTO administrador = service.findById(id);
            LOG.info("Buscando um usuarios por ID.");
            LOG.debug("Debug de busca de ID de usuarios.");
            return Response.ok(administrador).build();
        } catch (EntityNotFoundException e) {
            LOG.info("Erro ao buscar um usuarios por ID.");
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}

