package com.skinstore.dto;

public record AuthUsuarioDTO(
    String login,
    String senha,
    int perfil
) {
}
