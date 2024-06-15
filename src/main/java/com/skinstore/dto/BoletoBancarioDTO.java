package com.skinstore.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

public record BoletoBancarioDTO(
        @NotBlank(message = "O campo banco não pode ser nulo.")
        String banco,
        @NotBlank(message = "O campo número do boleto não pode ser nulo.")
        String numeroBoleto,
        @FutureOrPresent(message = "A data de vencimento não pode ser anterior ao dia atual")
        LocalDate dataVencimento) {
}