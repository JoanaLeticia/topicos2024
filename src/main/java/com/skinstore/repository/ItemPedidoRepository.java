package com.skinstore.repository;

import java.util.List;

import com.skinstore.model.Cliente;
import com.skinstore.model.ItemPedido;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemPedidoRepository implements PanacheRepository<ItemPedido> {
    public List<ItemPedido> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%" + nome + "%").list();
    }

    public List<ItemPedido> findItensByUsuario(Cliente cliente) {
        return find("select i from Pedido p join p.itens i where p.cliente = ?1", cliente).list();
    }

}