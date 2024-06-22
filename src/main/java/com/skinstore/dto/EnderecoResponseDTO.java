package com.skinstore.dto;

import com.skinstore.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String logradouro,
        String numero,
        String complemento,
        String cep,
        CidadeResponseDTO cidade) {
    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCep(),
                CidadeResponseDTO.valueOf(endereco.getCidade()));
    }
}
