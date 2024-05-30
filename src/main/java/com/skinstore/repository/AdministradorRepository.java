package com.skinstore.repository;

import java.util.List;

import com.skinstore.model.Administrador;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdministradorRepository implements PanacheRepository<Administrador> {
    public List<Administrador> findByNome(String nome) {
        return find("UPPER(pessoa.nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public Administrador findByLoginAndSenha(String login, String senha) {
        return find("pessoa.usuario.login = ?1 AND pessoa.usuario.senha = ?2", login, senha).firstResult();
    }
}
