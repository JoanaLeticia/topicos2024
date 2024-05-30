package com.skinstore.dto;

import java.util.List;

public record PessoaDTO(
    String nome,
    List<TelefoneDTO> telefones
) {
}
