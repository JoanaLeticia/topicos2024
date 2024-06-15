package com.skinstore.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.skinstore.model.Pedido;

public record PedidoResponseDTO(
        Long id,
        LocalDateTime horario,
        ClienteResponseDTO usuario,
        Double valorTotal,
        List<ItemPedidoResponseDTO> itens,
        EnderecoResponseDTO endereco) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataHora(),
                ClienteResponseDTO.valueOf(pedido.getCliente()),
                pedido.getValorTotal(),
                ItemPedidoResponseDTO.valueOf(pedido.getItens()),
                EnderecoResponseDTO.valueOf(pedido.getEndereco()));
    }
}
