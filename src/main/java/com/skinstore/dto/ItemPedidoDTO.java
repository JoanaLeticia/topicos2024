package com.skinstore.dto;

public record ItemPedidoDTO(
    Double valor,
    Integer quantidade,
    Long idProduto
) {
}
