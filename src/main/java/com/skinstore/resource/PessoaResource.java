package com.skinstore.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import com.skinstore.application.Result;
import com.skinstore.dto.PessoaDTO;
import com.skinstore.dto.PessoaResponseDTO;
import com.skinstore.service.PessoaService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
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
import jakarta.ws.rs.core.Response.Status;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

    @Inject
    PessoaService service;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(PessoaResource.class);

    @POST
    @RolesAllowed({"Admin"})
    public Response insert(PessoaDTO dto) throws Exception {
        LOG.debug("Debug de inserção de pessoas.");
        try {
            LOG.info("Inserindo pessoa");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de pessoas.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }

    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response update(PessoaDTO dto, @PathParam("id") Long id) {
        try {
            LOG.info("Atualizando pessoa");
            service.update(id, dto);
            return Response.noContent().build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug da atualização de pessoas.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        try {
            LOG.info("Deletando a pessoa");
            service.delete(id);
            return Response.noContent().build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug da exclusão da pessoa.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    @RolesAllowed({"Admin"})
    public Response findAll() {
        LOG.info("Buscando todos as pessoas.");
        LOG.debug("Debug de busca de lista de pessoas.");
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id) {
        try {
            PessoaResponseDTO a = service.findById(id);
            LOG.info("Buscando uma pessoa por ID.");
            LOG.debug("Debug de busca de ID de pessoas.");
            return Response.ok(a).build();
        } catch (EntityNotFoundException e) {
            LOG.info("Erro ao buscar uma pessoa por ID.");
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({"Admin"})
    public Response findByNome(@PathParam("nome") String nome) {
        try {
            LOG.info("Buscando uma pessoa por ID.");
            LOG.debug("Debug de busca de ID de pessoas.");
            return Response.ok(service.findByNome(nome)).build();
        } catch (EntityNotFoundException e) {
            LOG.info("Erro ao buscar uma pessoa por ID.");
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Transactional
    @Path("/updateNome/{nome}")
    @RolesAllowed({"Admin"})
    public Response updateNome(@PathParam("nome") String nome) {
        String login = jwt.getSubject();
        try {
            service.updateNome(login, nome);
            LOG.info("Nome atualizado!");
            return Response.ok("Informações da pessoa atualizadas com sucesso").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao atualizar informações da pessoa: " + e.getMessage())
                    .build();
        }
    }

    @PATCH
    @Transactional
    @Path("/updateCPF/{cpf}")
    @RolesAllowed({"Admin"})
    public Response updateCPF(@PathParam("cpf") String cpf) {
        String login = jwt.getSubject();
        try {
            service.updateCPF(login, cpf);
            LOG.info("CPF atualizado!");
            return Response.ok("Informações da pessoa atualizadas com sucesso").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao atualizar informações da pessoa: " + e.getMessage())
                    .build();
        }
    }
}