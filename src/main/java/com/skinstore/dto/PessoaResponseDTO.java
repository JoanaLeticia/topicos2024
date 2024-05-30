package com.skinstore.dto;

import java.util.List;

import com.skinstore.model.Pessoa;

public record PessoaResponseDTO(
    Long id,
    String nome,
    List<TelefoneResponseDTO> telefones
) {
    public static PessoaResponseDTO valueOf(Pessoa pessoa) {
        List<TelefoneResponseDTO> lista = pessoa.getListaTelefone()
                                            .stream()
                                            .map(TelefoneResponseDTO::valueOf)
                                            .toList();
        return new PessoaResponseDTO(
            pessoa.getId(),
            pessoa.getNome(),
            lista
        );
    }
}
