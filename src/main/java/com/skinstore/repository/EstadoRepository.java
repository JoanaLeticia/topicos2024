package com.skinstore.repository;

import java.util.List;

import com.skinstore.model.Estado;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado>{
    public List<Estado> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+nome+"%").list();
    }

    public List<Estado> findBySigla(String sigla) {
        return find("UPPER(sigla) LIKE ?1", "%"+ sigla.toUpperCase() + "%").list();
    }

    public Estado findByNomeCompleto(String nome) {
        return find("UPPER(nome) = ?1",  nome.toUpperCase() ).firstResult();
    }
}
