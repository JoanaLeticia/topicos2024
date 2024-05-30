package com.skinstore.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends DefaultEntity {
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pessoa")
    private List<Telefone> listaTelefone;

    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Telefone> getListaTelefone() {
        return listaTelefone;
    }

    public void setListaTelefone(List<Telefone> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

}
