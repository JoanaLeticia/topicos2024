package com.skinstore.dto;

import java.time.LocalDate;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

public record CartaoCreditoDTO(
     @NotBlank(message = "O campo bandeira não pode ser nulo.")
     String bandeira,
     @NotBlank(message = "O campo numero do cartão não pode ser nulo.")
     String numeroCartao,
     @NotBlank(message = "O campo codigo de seguranca não pode ser nulo.")
     String codigoSeguranca,
     @FutureOrPresent(message = "A data de vencimento não pode ser anterior ao dia atual")
     LocalDate dataVencimento,
     @NotNull
     Integer quantParcelas){

     }

