package com.skinstore.dto;

import com.skinstore.model.Administrador;

public record AdministradorResponseDTO(
        Long id,
        String nome,
        String cpf,
        String login,
        Integer matricula) {
    public static AdministradorResponseDTO valueOf(Administrador adm) {
        return new AdministradorResponseDTO(
                adm.getId(),
                adm.getPessoa().getNome(),
                adm.getPessoa().getCpf(),
                adm.getPessoa().getUsuario().getLogin(),
                adm.getMatricula());
    }
}

