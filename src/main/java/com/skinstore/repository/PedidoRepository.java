package com.skinstore.repository;

import java.util.List;

import com.skinstore.model.Cliente;
import com.skinstore.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    public List<Pedido> findAll(String login) {
        return find("cliente.login = ?1", login).list();
    }

    public List<Pedido> findAll(Long idUsuario) {
        return find("cliente.id = ?1", idUsuario).list();
    }

    public List<Pedido> findByUsuario(Cliente cliente) {
        return find("cliente = ?1", cliente).list();
    }

    public Pedido obterUltimoPedidoDoCliente(Long idCliente) {
        return find("cliente.id = ?1 order by id desc", idCliente)
                .firstResultOptional()
                .orElse(null);
    }
    

}
