package com.skinstore.resource;

import com.skinstore.dto.ClienteDTO;
import com.skinstore.service.ClienteService;

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

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/clientes")
public class ClienteResource {
    
    @Inject
    public ClienteService clienteService;

    @GET
    @Path("/{id}")
    public Response findByiD(@PathParam("id") Long id) {
        return Response.ok(clienteService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(clienteService.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(clienteService.findByNome(nome)).build();
    }

    @POST
    @Transactional
    public Response create(ClienteDTO clienteDTO) {
        return Response.status(Status.CREATED).entity(clienteService.create(clienteDTO)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ClienteDTO clienteDTO) {
        clienteService.update(id, clienteDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        clienteService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}
