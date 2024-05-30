package com.skinstore.repository;

import java.util.List;

import com.skinstore.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    public List<Pedido> findByCliente(Long idCliente) {
        return find("UPPER(cliente.id) = ?1", idCliente).list();
    }
}
