package com.skinstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO (
    @NotBlank(message = "Por favor, insira o nome.")
    String nome,
    @JsonProperty(required = true)
    @NotBlank(message = "Digite seu e-mail de login.")
    String login,
    @NotBlank(message = "Por favor, insira sua senha.")
    String senha
) {

}
