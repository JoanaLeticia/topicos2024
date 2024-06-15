package com.skinstore.dto;

import com.skinstore.model.Pagamento;
import com.skinstore.model.StatusPagamento;

public record PagamentoResponseDTO(
        Long id,
        String tipo,
        Double valor,
        PedidoResponseDTO pedidoDTO,
        StatusPagamento statusPagamento) {
    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getTipo(),
                pagamento.getValor(),
                PedidoResponseDTO.valueOf(pagamento.getPedido()),
                pagamento.getStatusPagamento());
    }
}
