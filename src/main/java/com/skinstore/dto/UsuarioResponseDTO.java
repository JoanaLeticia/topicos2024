package com.skinstore.dto;

import com.skinstore.model.Usuario;

public record UsuarioResponseDTO (
    Long id,
    String nome,
    String login
) {
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getLogin());
    }
}
