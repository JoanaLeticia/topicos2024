package com.skinstore.dto;

import com.skinstore.model.Administrador;
import com.skinstore.model.Perfil;

public record AdministradorResponseDTO(
        Long id,
        String nome,
        String cpf,
        String login,
        Perfil perfil,
        Integer matricula) {
    public static AdministradorResponseDTO valueOf(Administrador adm) {
        return new AdministradorResponseDTO(
                adm.getId(),
                adm.getPessoa().getNome(),
                adm.getPessoa().getCpf(),
                adm.getPessoa().getUsuario().getLogin(),
                adm.getPessoa().getUsuario().getPerfil(),
                adm.getMatricula());
    }
}

