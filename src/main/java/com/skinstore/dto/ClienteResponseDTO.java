package com.skinstore.dto;

import java.util.List;

import com.skinstore.model.Cliente;

public record ClienteResponseDTO (
    Long id,
    String nome,
    String login,
    String senha,
    String cpf,
    List<TelefoneResponseDTO> telefones
) {
    public static ClienteResponseDTO valueOf(Cliente cliente) {
        List<TelefoneResponseDTO> listaDeTelefones = cliente.getPessoa().getListaTelefone()
                                            .stream()
                                            .map(TelefoneResponseDTO::valueOf)
                                            .toList();
        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getPessoa().getNome(),
            cliente.getPessoa().getUsuario().getLogin(),
            cliente.getPessoa().getUsuario().getSenha(),
            cliente.getCpf(),
            listaDeTelefones);
    }
}
