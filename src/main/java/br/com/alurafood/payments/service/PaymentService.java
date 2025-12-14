package br.com.alurafood.payments.service;

import br.com.alurafood.payments.dto.CreatePaymentDto;
import br.com.alurafood.payments.dto.PaymentDto;
import br.com.alurafood.payments.dto.UpdatePaymentDto;
import br.com.alurafood.payments.model.Payment;
import br.com.alurafood.payments.model.Status;
import br.com.alurafood.payments.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    public Page<PaymentDto> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(PaymentDto::new);
    }

    public PaymentDto getById(@NotNull Long id) {
        return repository.findById(id)
                .map(PaymentDto::new)
                .orElse(null);
    }

    @Transactional
    public PaymentDto createPayment(@Valid CreatePaymentDto dto) {
        Payment payment = new Payment(dto);

        return new PaymentDto(repository.save(payment));

    }

    @Transactional
    public PaymentDto updatePayment(@Valid UpdatePaymentDto dto) {
        return repository.findById(dto.id())
                .map(payment -> {
                    if (dto.value() != null) {
                        payment.setValue(dto.value());
                    }
                    if (dto.code() != null) {
                        payment.setCode(dto.code());
                    }
                    if (dto.period() != null) {
                        payment.setPeriod(dto.period());
                    }
                    if (dto.expiration() != null) {
                        payment.setExpiration(dto.expiration());
                    }
                    if (dto.status() != null) {
                        payment.setStatus(Status.valueOf(dto.status().name()));
                    }
                    return new PaymentDto(repository.save(payment));
                })
                .orElse(null);
    }


    @Transactional
    public void deletePayment(@NotNull Long id) {
        repository.findById(id)
                .map(payment -> {
                    repository.delete(payment);
                    return true;
                });
    }

}
