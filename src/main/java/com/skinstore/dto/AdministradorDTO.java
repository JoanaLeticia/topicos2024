package com.skinstore.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdministradorDTO (
    @NotBlank(message = "Digite o seu nome.") String nome,
    @NotNull(message = "Digite a sua inscrição") Integer inscricao,
    @NotBlank(message = "Digite seu username.") String login,
    @NotBlank(message = "Por favor, insira sua senha.") String senha,
    List<TelefoneDTO> telefones
) {
    
}
