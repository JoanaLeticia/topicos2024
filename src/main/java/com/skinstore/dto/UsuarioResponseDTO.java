package com.skinstore.dto;

import com.skinstore.model.Perfil;
import com.skinstore.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String login,
    Perfil perfil
) { 
    public static UsuarioResponseDTO valueOf(Usuario usuario){
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getLogin(),
            usuario.getPerfil()
        );
    }
    
    
}
