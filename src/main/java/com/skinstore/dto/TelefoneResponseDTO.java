package com.skinstore.dto;

import com.skinstore.model.Telefone;

public record TelefoneResponseDTO(
        String codigoArea,
        String numero) {
    public static TelefoneResponseDTO valueOf(Telefone telefone) {
        return new TelefoneResponseDTO(
                telefone.getCodigoArea(),
                telefone.getNumero());
    }
}
