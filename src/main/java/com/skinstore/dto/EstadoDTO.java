package com.skinstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EstadoDTO (
    @NotBlank(message = "Insira o nome.")
    @Size(min = 4, max = 60, message = "O nome deve ter entre 2 e 60 caracteres") 
    String nome,
    @NotNull(message = "Insira a sigla.")
    @Size(min = 2, max = 2, message = "A sigla deve ter 2 caracteres")
    String sigla
) {
    
}
