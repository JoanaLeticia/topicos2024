package com.skinstore.dto;

import com.skinstore.model.Pessoa;

public record UsuarioResponseDTO (
    String nome,
    String login
) {
    public static UsuarioResponseDTO valueOf(Pessoa pessoa) {
        return new UsuarioResponseDTO(
            pessoa.getNome(),
            pessoa.getUsuario().getLogin()
        );
    }
}
