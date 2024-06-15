package com.skinstore.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record PagamentoDTO(
        @NotBlank(message = "O campo tipo não pode ser nulo")
        String tipo,
        List<PixDTO> pix,
        List<BoletoBancarioDTO> boletoBancario,
        List<CartaoCreditoDTO> cartaoCredito) {
}
