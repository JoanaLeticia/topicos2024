package com.skinstore.repository;

import java.util.List;

import com.skinstore.model.Cliente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    public List<Cliente> findByNome(String nome) {
        return find("UPPER(pessoa.nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }
}
