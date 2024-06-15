package com.skinstore.resource;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.skinstore.application.Result;
import com.skinstore.dto.ProdutoDTO;
import com.skinstore.dto.ProdutoResponseDTO;
import com.skinstore.form.ProdutoImageForm;
import com.skinstore.service.ProdutoFileService;
import com.skinstore.service.ProdutoService;
import com.skinstore.service.UsuarioService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
    @Inject
    ProdutoService service;

    @Inject
    JsonWebToken jwt;

    @Inject
    ProdutoFileService fileService;

    @Inject
    UsuarioService usuarioService;

    private static final Logger LOG = Logger.getLogger(ProdutoResource.class);

    @POST
    @RolesAllowed({"Admin"})
    public Response insert(ProdutoDTO dto) throws Exception {
        LOG.debug("Debug de inserção de Produto.");
        try {
            LOG.info("Inserindo Produto");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug de inserção de Produto.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }

    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response update(ProdutoDTO dto, @PathParam("id") Long id) {
        try {
            LOG.info("Atualizando Produto");
            service.update(dto, id);
            return Response.noContent().build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug da atualização de Produto.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        try {
            LOG.info("Deletando o Produto");
            service.delete(id);
            return Response.noContent().build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.debug("Debug da exclusão do Produto.");
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    @RolesAllowed({"Admin"})
    public Response findAll() {
        LOG.info("Buscando todos os Produto.");
        LOG.debug("Debug de busca de lista de Produto.");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id) {
        try {
            ProdutoResponseDTO a = service.findById(id);
            LOG.info("Buscando um Produto por ID.");
            LOG.debug("Debug de busca de ID de Produto.");
            return Response.ok(a).build();
        } catch (EntityNotFoundException e) {
            LOG.error("Erro ao buscar um Produto por ID.");
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({"Admin"})
    public Response findByNome(@PathParam("nome") String nome) {
        try {
            LOG.info("Buscando um Produto pelo nome.");
            LOG.debug("Debug de busca pelo nome Produto.");
            return Response.ok(service.findByNome(nome)).build();
        } catch (EntityNotFoundException e) {
            LOG.error("Erro ao buscar pelo nome do Produto.");
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // Imagens:

    @PATCH
    @Path("/upload/imagem/{id}")
    @RolesAllowed({"Admin"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ProdutoImageForm form, @PathParam("id") Long id) throws IOException {
        String nomeImagem;
        nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
        ProdutoResponseDTO imagemCAPA = service.findById(id);
        imagemCAPA = service.updateNomeImagem(imagemCAPA.id(), nomeImagem);

        return Response.ok(imagemCAPA).build();

    }
}
