package br.com.alurafood.payments.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private BigDecimal value;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 19)
    private String number;

    @NotBlank
    @Size(max = 7)
    private String expiration;

    @NotBlank
    @Size(min = 3, max = 3)
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Long period;

    @NotNull
    private Long paymentType;

}
