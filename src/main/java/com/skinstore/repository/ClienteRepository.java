package com.skinstore.repository;

import java.util.List;

import org.jboss.logging.Logger;

import com.skinstore.model.Cliente;
import com.skinstore.resource.ClienteResource;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente>{

    private static final Logger LOG = Logger.getLogger(ClienteResource.class);

    public List<Cliente> findByNome(String nome) {
        return find("UPPER(pessoa.nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public Cliente findByLogin(String login) {
        try {
            return find("login = ?1", login).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cliente findByLoginAndSenha(String login, String senha) {
        // Log de consulta ao banco de dados
        LOG.info("Procurando cliente com login: " + login + " e senha fornecida");
        
        return find("pessoa.usuario.login = ?1 AND pessoa.usuario.senha = ?2", login, senha).firstResult();
    }
}