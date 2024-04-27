package com.skinstore.resource;

import com.skinstore.dto.ProdutoDTO;
import com.skinstore.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
    @Inject
    ProdutoService produtoService;

    @GET
    @Path("/{id}")
    public Response findByiD(@PathParam("id") Long id) {
        return Response.ok(produtoService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(produtoService.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(produtoService.findByNome(nome)).build();
    }

    @POST
    @Transactional
    public Response create(ProdutoDTO produtoDTO) {
        return Response.status(Status.CREATED).entity(produtoService.create(produtoDTO)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ProdutoDTO produtoDTO) {
        produtoService.update(id, produtoDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        produtoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}
