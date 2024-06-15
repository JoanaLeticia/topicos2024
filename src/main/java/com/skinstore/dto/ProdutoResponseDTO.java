package com.skinstore.dto;

import java.math.BigDecimal;

import com.skinstore.model.Arma;
import com.skinstore.model.Disponibilidade;
import com.skinstore.model.Exterior;
import com.skinstore.model.Produto;
import com.skinstore.model.Tipo;

public record ProdutoResponseDTO(
    Long id,
    String nome,
    String linkSteam,
    BigDecimal valor,
    Integer quantEstoque,
    Tipo tipo,
    Arma arma,
    Exterior exterior,
    Float numeroFloat,
    Integer pattern,
    Disponibilidade disponibilidade,
    String nomeImagem
) {
    public static ProdutoResponseDTO valueOf(Produto produto) {
        return new ProdutoResponseDTO(
            produto.getId(),
            produto.getNome(),
            produto.getLinkSteam(),
            produto.getValor(),
            produto.getQuantEstoque(),
            produto.getTipo(),
            produto.getArma(),
            produto.getExterior(),
            produto.getNumeroFloat(),
            produto.getPattern(),
            produto.getDisponibilidade(),
            produto.getNomeImagem());
    }
}
