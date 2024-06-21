package com.skinstore.dto;

import com.skinstore.model.Pessoa;

public record PessoaResponseDTO(
    String nome,
    String cpf,
    String login
) {
    public static PessoaResponseDTO valueOf(Pessoa pessoa) {
        return new PessoaResponseDTO(
            pessoa.getNome(),
            pessoa.getCpf(),
            pessoa.getUsuario().getLogin()
        );
    }
}
