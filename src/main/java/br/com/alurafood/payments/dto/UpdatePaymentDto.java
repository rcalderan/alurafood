package br.com.alurafood.payments.dto;

import br.com.alurafood.payments.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdatePaymentDto(
        @NotNull
        Long id,

        @Positive
        BigDecimal value,

        @Size(min = 3, max = 3)
        String code,

        Status status,

        @Size(max = 7)
        String expiration,

        Long period) {
}
