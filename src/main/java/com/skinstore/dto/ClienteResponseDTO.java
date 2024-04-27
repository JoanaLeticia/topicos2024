package com.skinstore.dto;

import java.util.List;

import com.skinstore.model.Cliente;

public record ClienteResponseDTO (
    Long id,
    String nome,
    String login,
    String cpf,
    List<TelefoneResponseDTO> telefones
) {
    public static ClienteResponseDTO valueOf(Cliente cliente) {
        if (cliente == null) {
            return new ClienteResponseDTO(null, null, null, null, null);
        }
        List<TelefoneResponseDTO> listaDeTelefones = cliente.getListaTelefone()
                                            .stream()
                                            .map(TelefoneResponseDTO::valueOf)
                                            .toList();
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getLogin(),
            cliente.getCpf(),
            listaDeTelefones);
    }
}
