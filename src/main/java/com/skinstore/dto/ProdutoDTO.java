package com.skinstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO (
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    @NotBlank(message = "O campo link da steam não pode ser nulo.")
    String linkSteam,
    @NotNull(message = "O campo valor não pode ser nulo.")
    Double valor,
    @NotNull(message = "O campo quantidade não pode ser nulo.")
    Integer quantEstoque,
    Integer idTipo,
    Integer idArma,
    Integer idExterior,
    @NotNull(message = "O campo numero do float não pode ser nulo.")
    Float numeroFloat,
    @NotNull(message = "O campo pattern não pode ser nulo.")
    Integer pattern,
    Integer idDisponibilidade
) {
    
}
