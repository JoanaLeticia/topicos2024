package com.skinstore.dto;

import com.skinstore.model.ItemPedido;

public record ItemPedidoResponseDTO(
        Long id,
        String nome,
        Integer quantidade) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido item) {
        return new ItemPedidoResponseDTO(
                item.getId(),
                item.getProduto().getNome(),
                item.getQuantidade());
    }
}
