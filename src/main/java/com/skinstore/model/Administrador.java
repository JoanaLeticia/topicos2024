package com.skinstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Administrador extends DefaultEntity {

    @Column(length = 10)
    private Integer inscricao;

    @OneToOne
    @JoinColumn(name = "id_pessoa", unique = true)
    private Pessoa pessoa;

    private String nomeImagem;

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public Integer getInscricao() {
        return inscricao;
    }

    public void setInscricao(Integer inscricao) {
        this.inscricao = inscricao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}