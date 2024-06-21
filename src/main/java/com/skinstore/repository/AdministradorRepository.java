package com.skinstore.repository;

import java.util.List;
import org.jboss.logging.Logger;

import com.skinstore.model.Administrador;
import com.skinstore.resource.AdministradorResource;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdministradorRepository implements PanacheRepository<Administrador> {

    private static final Logger LOG = Logger.getLogger(AdministradorResource.class);
    
    public List<Administrador> findByNome(String nome) {
        return find("UPPER(pessoa.nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public Administrador findByLoginAndSenha(String login, String senha) {
        // Log de consulta ao banco de dados
        LOG.info("Procurando administrador com login: " + login + " e senha fornecida");
        
        return find("pessoa.usuario.login = ?1 AND pessoa.usuario.senha = ?2", login, senha).firstResult();
    }
}

