package com.skinstore.dto;

import com.skinstore.model.Pessoa;

public record UsuarioResponseDTO(
    String login,
    String nome,
    int perfil
) { 
    public static UsuarioResponseDTO valueOf(Pessoa pessoa){
        return new UsuarioResponseDTO(
            pessoa.getUsuario().getLogin(),
            pessoa.getNome(),
            pessoa.getUsuario().getPerfil()
        );
    }
    
    
}
