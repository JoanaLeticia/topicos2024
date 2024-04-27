package com.skinstore.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.skinstore.model.Tipo;
import com.skinstore.model.Arma;
import com.skinstore.model.Exterior;
import com.skinstore.model.Disponibilidade;

public record ProdutoDTO (
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    @NotBlank(message = "O campo link da steam não pode ser nulo.")
    String linkSteam,
    @NotNull(message = "O campo valor não pode ser nulo.")
    BigDecimal valor,
    Tipo tipo,
    Arma arma,
    Exterior exterior,
    @NotBlank(message = "O campo numero do float não pode ser nulo.")
    Float numeroFloat,
    @NotBlank(message = "O campo pattern não pode ser nulo.")
    Integer pattern,
    Disponibilidade disponibilidade
) {
    
}
