package com.skinstore.dto;

import com.skinstore.model.Cidade;
import com.skinstore.model.Estado;

public record CidadeResponseDTO(
        Long id,
        String nome,
        Estado estado) {
    public static CidadeResponseDTO valueOf(Cidade cidade){
        return new CidadeResponseDTO(cidade.getId(), cidade.getNome(), cidade.getEstado());
    }
}
