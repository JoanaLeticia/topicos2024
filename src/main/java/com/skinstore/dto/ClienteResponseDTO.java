package com.skinstore.dto;

import java.util.List;

import com.skinstore.model.Cliente;
import com.skinstore.model.Perfil;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String cpf,
    String login,
    Perfil perfil,
    List<TelefoneResponseDTO> listaTelefone
) { 
    public static ClienteResponseDTO valueOf(Cliente cliente){
        if (cliente == null) {
            return new ClienteResponseDTO(null, null, null, null, null, null);
        }

        List<TelefoneResponseDTO> listaTelefones = cliente.getTelefones()
                                                    .stream()
                                                    .map(TelefoneResponseDTO::valueOf)
                                                    .toList();
        return new ClienteResponseDTO(
            cliente.getId(), 
            cliente.getPessoa().getNome(),
            cliente.getPessoa().getCpf(),
            cliente.getPessoa().getUsuario().getLogin(),
            cliente.getPessoa().getUsuario().getPerfil(),
            listaTelefones
        );
    }
}