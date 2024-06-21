package com.skinstore.repository;

import java.util.List;

import com.skinstore.model.Pessoa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {
    public Pessoa findByLogin(String login) {
        try {
            return find("usuario.login = ?1 ", login).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public List<Pessoa> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();
    }

}
