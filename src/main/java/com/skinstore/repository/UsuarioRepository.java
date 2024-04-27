package com.skinstore.repository;

import java.util.List;

import com.skinstore.model.Usuario;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public List<Usuario> findByLogin(String login) {
        return find("UPPER(login) LIKE ?1", "%" + login.toUpperCase() + "%").list();
    }
}
