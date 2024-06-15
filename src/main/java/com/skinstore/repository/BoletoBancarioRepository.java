package com.skinstore.repository;

import java.util.List;

import com.skinstore.model.BoletoBancario;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BoletoBancarioRepository implements PanacheRepository<BoletoBancario>{
    public List<BoletoBancario> findByNumeroBoleto(String numeroBoleto) {
        return find("UPPER(numeroBoleto) LIKE UPPER(?1) ", "%"+numeroBoleto+"%").list();
    }

}
