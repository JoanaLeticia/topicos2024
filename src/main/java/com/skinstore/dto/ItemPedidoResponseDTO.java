package com.skinstore.dto;

import java.util.List;

import com.skinstore.model.ItemPedido;

public record ItemPedidoResponseDTO(
        Integer quantidade,
        Double valor,
        Long idProduto,
        String nome
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido item) {
        return new ItemPedidoResponseDTO(
            item.getQuantidade(),
            item.getValor(),
            item.getProduto().getId(),
            item.getProduto().getNome()
        );
    }

    public static List<ItemPedidoResponseDTO> valueOf(List<ItemPedido> item) {
        return item.stream().map(i -> ItemPedidoResponseDTO.valueOf(i)).toList();
    }
}
