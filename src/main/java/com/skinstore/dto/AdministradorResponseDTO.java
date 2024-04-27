package com.skinstore.dto;

import com.skinstore.model.Administrador;

public record AdministradorResponseDTO (
    Long id,
    String nome,
    String login,
    Integer inscricao
) {
    public static AdministradorResponseDTO valueOf(Administrador adm) {
        return new AdministradorResponseDTO(
            adm.getId(),
            adm.getNome(),
            adm.getLogin(),
            adm.getInscricao());
    }
}
