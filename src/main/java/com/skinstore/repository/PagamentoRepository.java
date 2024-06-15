package com.skinstore.repository;

import java.util.List;

import com.skinstore.model.Pagamento;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {
    public List<Pagamento> findByTipo(String tipo) {
        return find("UPPER(tipo) LIKE UPPER(?1) ", "%" + tipo + "%").list();
    }
    public boolean existsByPedidoId(Long idPedido) {
        return count("pedido.id = ?1", idPedido) > 0;
    }
}