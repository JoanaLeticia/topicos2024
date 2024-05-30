package com.skinstore.dto;

import java.util.List;

import com.skinstore.model.Administrador;

public record AdministradorResponseDTO (
    Long id,
    String nome,
    String login,
    String senha,
    String nomeImagem,
    Integer inscricao,
    List<TelefoneResponseDTO> telefones
) {
    public static AdministradorResponseDTO valueOf(Administrador adm) {
        List<TelefoneResponseDTO> lista = adm.getPessoa().getListaTelefone()
                                            .stream()
                                            .map(TelefoneResponseDTO::valueOf)
                                            .toList();
        return new AdministradorResponseDTO(
            adm.getId(),
            adm.getPessoa().getNome(),
            adm.getPessoa().getUsuario().getLogin(),
            adm.getPessoa().getUsuario().getSenha(),
            adm.getNomeImagem(),
            adm.getInscricao(),
            lista);
    }
}
