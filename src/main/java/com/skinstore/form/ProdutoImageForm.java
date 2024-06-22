package com.skinstore.form;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;

public class ProdutoImageForm {

    @FormParam("nomeImagem")
    @PartType("text/plain")
    private String nomeImagem;

    @FormParam("imagem")
    @PartType("application/octet-stream")
    private byte[] imagem;

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
