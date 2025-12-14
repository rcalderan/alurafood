package br.com.alurafood.payments.dto;

import br.com.alurafood.payments.model.Payment;
import br.com.alurafood.payments.model.Status;

import java.math.BigDecimal;

public record PaymentDto(Long id,
        BigDecimal value,
        String name,
        String number,
        String expiration,
        String code,
        Status status,
        Long period,
        Long paymentType) {
    public PaymentDto(Payment payment) {
        this(payment.getId(),
                payment.getValue(),
                payment.getName(),
                payment.getNumber(),
                payment.getExpiration(),
                payment.getCode(),
                payment.getStatus(),
                payment.getPeriod(),
                payment.getPaymentType());
    }
}
