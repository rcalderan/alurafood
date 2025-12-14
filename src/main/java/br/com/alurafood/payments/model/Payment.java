package br.com.alurafood.payments.model;

import br.com.alurafood.payments.dto.CreatePaymentDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    @Setter
    private BigDecimal value;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 19)
    private String number;

    @NotBlank
    @Size(max = 7)
    @Setter
    private String expiration;

    @NotBlank
    @Size(min = 3, max = 3)
    @Setter
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Setter
    private Status status;

    @NotNull
    @Setter
    private Long period;

    @NotNull
    private Long paymentType;

    public Payment(CreatePaymentDto dto){
        this.value = dto.value();
        this.name = dto.name();
        this.number = dto.number();
        this.code = dto.code();
        this.paymentType = dto.paymentType();

        this.status = Status.CREATED;
        this.period = 3L;
        this.expiration = LocalDateTime.now().plusDays(5L).format(DateTimeFormatter.ofPattern("dd/MM")).toUpperCase();
    }

}
