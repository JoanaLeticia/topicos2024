package com.skinstore.dto;

import java.util.List;

public record PedidoDTO(
    Long idCliente,
    List<ItemPedidoDTO> itens
) {
    
}
