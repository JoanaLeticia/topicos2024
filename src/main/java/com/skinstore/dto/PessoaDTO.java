package com.skinstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PessoaDTO(
    @NotBlank(message = "Digite o seu nome.")
    String nome,
    @NotBlank(message = "O campo CPF não pode ser nulo.")
    @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$", message = "CPF inválido")
    String cpf,
    @NotBlank(message = "O campo email não pode ser nulo.")
    @Email(message = "O campo email não esta no formato correto!")
    String email,
    @NotBlank(message = "O campo senha não pode ser nulo.")
    String senha,
    Integer idPerfil
) {
}
