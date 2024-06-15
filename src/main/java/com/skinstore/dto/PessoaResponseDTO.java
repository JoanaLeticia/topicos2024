package com.skinstore.dto;

import com.skinstore.model.Perfil;
import com.skinstore.model.Pessoa;

public record PessoaResponseDTO(
    String nome,
    String cpf,
    String login,
    Perfil perfil
) {
    public static PessoaResponseDTO valueOf(Pessoa pessoa) {
        return new PessoaResponseDTO(
            pessoa.getNome(),
            pessoa.getCpf(),
            pessoa.getUsuario().getLogin(),
            pessoa.getUsuario().getPerfil()
        );
    }
}
