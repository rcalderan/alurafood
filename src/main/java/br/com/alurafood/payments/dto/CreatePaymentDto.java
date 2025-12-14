package br.com.alurafood.payments.dto;

import br.com.alurafood.payments.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreatePaymentDto(
        @NotNull
        @Positive
        BigDecimal value,

        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Size(max = 19)
        String number,

        @NotBlank
        @Size(min = 3, max = 3)
        String code,

        @NotNull
        Long paymentType) {
}
