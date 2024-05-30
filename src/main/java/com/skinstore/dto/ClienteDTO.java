package com.skinstore.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record ClienteDTO (
    @NotBlank(message = "Digite o seu nome.") String nome,
    @NotBlank(message = "Digite seu username.") String login,
    @NotBlank(message = "Por favor, insira sua senha.") String senha,
    @NotBlank(message = "Digite o seu CPF") String cpf,
    List<TelefoneDTO> telefones
) {
    
}
