package com.skinstore.dto;

import java.time.LocalDate;

import com.skinstore.model.BoletoBancario;

public record BoletoBancarioResponseDTO(
        Long id,
        String banco,
        String numeroBoleto,
        LocalDate dataVencimento) {
    public static BoletoBancarioResponseDTO valueOf(BoletoBancario boleto) {
        return new BoletoBancarioResponseDTO(
                boleto.getId(),
                boleto.getBanco(),
                boleto.getNumeroBoleto(),
                boleto.getDataVencimento());
    }
}
