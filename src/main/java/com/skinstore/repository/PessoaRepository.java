package com.skinstore.repository;

import java.util.List;

import com.skinstore.model.Pessoa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {

    public List<Pessoa> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();
    }

}
