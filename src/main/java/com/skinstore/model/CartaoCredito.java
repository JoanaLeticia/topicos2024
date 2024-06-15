package com.skinstore.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class CartaoCredito extends DefaultEntity {
    @Column(length = 25)
    private String bandeira;

    @Column(length = 16)
    private String numeroCartao;

    @Column(length = 3)
    private String codigoSeguranca;

    private LocalDate dataVencimento;
    
    private Integer quantidadeParcelas;

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Integer getQuantidadeParcelas(){
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas){
        this.quantidadeParcelas = quantidadeParcelas;
    }
}