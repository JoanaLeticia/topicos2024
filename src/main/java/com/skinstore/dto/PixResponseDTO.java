package com.skinstore.dto;

import com.skinstore.model.Pix;

public record PixResponseDTO(
        Long id,
        String chavePix) {

    public static PixResponseDTO valueOf(Pix pix) {
        return new PixResponseDTO(
                pix.getId(),
                pix.getChavePix());
    }
}
