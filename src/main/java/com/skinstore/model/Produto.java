package com.skinstore.model;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Produto extends DefaultEntity {
    @Column(length = 60)
    private String nome;

    @Column(length = 200)
    private String linkSteam;

    @Column
    private BigDecimal valor;

    // Cada produto é único
    @Column(columnDefinition = "INTEGER CHECK (quantEstoque >= 0)")
    private Integer quantEstoque = 1;

    @Column
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column
    @Enumerated(EnumType.STRING)
    private Arma arma;

    @Column
    @Enumerated(EnumType.STRING)
    private Exterior exterior;

    @Column
    private Float numeroFloat;

    @Column
    private Integer pattern;

    @Column
    @Enumerated(EnumType.STRING)
    private Disponibilidade disponibilidade;

    private String nomeImagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLinkSteam() {
        return linkSteam;
    }

    public void setLinkSteam(String linkSteam) {
        this.linkSteam = linkSteam;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getQuantEstoque() {
        return quantEstoque;
    }

    public void setQuantEstoque(Integer quantEstoque) {
        this.quantEstoque = quantEstoque;
    }

    public void setNumeroFloat(Float numeroFloat) {
        this.numeroFloat = numeroFloat;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public Exterior getExterior() {
        return exterior;
    }

    public void setExterior(Exterior exterior) {
        this.exterior = exterior;
    }

    public float getNumeroFloat() {
        return numeroFloat;
    }

    public void setNumeroFloat(float numeroFloat) {
        this.numeroFloat = numeroFloat;
    }

    public Integer getPattern() {
        return pattern;
    }

    public void setPattern(Integer pattern) {
        this.pattern = pattern;
    }

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

}
