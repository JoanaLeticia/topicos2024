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

}