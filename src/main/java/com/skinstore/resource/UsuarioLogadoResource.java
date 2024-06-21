package com.skinstore.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import com.skinstore.dto.ItemPedidoResponseDTO;
import com.skinstore.dto.PedidoResponseDTO;
import com.skinstore.model.Cliente;
import com.skinstore.repository.ClienteRepository;
import com.skinstore.repository.UsuarioRepository;
import com.skinstore.service.ClienteService;
import com.skinstore.service.PedidoService;
import com.skinstore.service.UsuarioService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioRepository repository;

    @Inject
    PedidoService pedidoService;

    @Inject
    ClienteService clienteService;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    UsuarioService usuarioService;

    private static final Logger LOG = Logger.getLogger(UsuarioLogadoResource.class);

    /*@GET
    @RolesAllowed({ "Cliente", "Admin" })
    public Response getUsuario() {
        // obtendo o login pelo token jwt
        String login = jwt.getSubject();
        try {
            LOG.info("obtendo o login pelo token jwt");
            LOG.info("Retornando login");
            return Response.ok(usuarioService.findByLogin(login)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao retornar informações do usuário logado: " + e.getMessage())
                    .build();
            
        }
    }

    @PATCH
    @Transactional
    @Path("/updateSenha/{senha}")
    @RolesAllowed({ "Cliente", "Admin" })
    public Response updateSenha(@PathParam("senha") String senha) {
        String login = jwt.getSubject();
        try {
            usuarioService.updateSenha(login, senha);
            LOG.info("Senha atualizada!");
            return Response.ok("Informações do usuário atualizadas com sucesso").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao atualizar informações do usuário: " + e.getMessage())
                    .build();
        }
    }*/

    @GET
    @RolesAllowed({ "Cliente", "Admin" })
    @Path("/PedidosDoUsuario")
    public Response getPedidosUsuario() {
        String login = jwt.getSubject();
        Cliente usuarioLogado = clienteRepository.findByLogin(login);

        if (usuarioLogado != null) {
            List<PedidoResponseDTO> pedidos = pedidoService.pedidosUsuarioLogado(usuarioLogado);
            LOG.info("Retornando pedidos do usuário: " + login);
            return Response.ok(pedidos).build();
        } else {
            LOG.error("Usuário não encontrado: " + login);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @RolesAllowed({ "Cliente", "Admin" })
    @Path("/ItensDasComprasUsuario")
    public Response getItensPedidosUsuario() {
        String login = jwt.getSubject();
        Cliente usuarioLogado = clienteRepository.findByLogin(login);

        if (usuarioLogado != null) {
            List<ItemPedidoResponseDTO> itens = pedidoService.findItensByUsuario(usuarioLogado);
            LOG.info("Retornando itens dos pedidos do usuário: " + login);
            return Response.ok(itens).build();
        } else {
            LOG.error("Usuário não encontrado: " + login);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}