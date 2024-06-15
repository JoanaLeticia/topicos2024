package com.skinstore.dto;

import com.skinstore.model.Cidade;
import com.skinstore.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String numero,
        String complemento,
        String cep,
        Cidade cidade) {
    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCep(),
                endereco.getCidade());
    }
}
