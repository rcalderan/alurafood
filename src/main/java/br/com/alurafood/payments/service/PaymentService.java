package br.com.alurafood.payments.service;

import br.com.alurafood.payments.dto.CreatePaymentDto;
import br.com.alurafood.payments.dto.PaymentDto;
import br.com.alurafood.payments.dto.UpdatePaymentDto;
import br.com.alurafood.payments.model.Payment;
import br.com.alurafood.payments.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PaymentDto> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(p -> modelMapper.map(p, PaymentDto.class));
    }

    public PaymentDto getById(@NotNull Long id) {
        var response = repository.getReferenceById(id);
        return new PaymentDto(response);
    }

    @Transactional
    public PaymentDto createPayment(@Valid CreatePaymentDto dto) {
        Payment payment = new Payment(dto);

        return new PaymentDto(repository.save(payment));

    }

    @Transactional
    public PaymentDto updatePayment( @Valid UpdatePaymentDto dto) {
        var paymentToBeUpdated = repository.getReferenceById(dto.id());

        if (paymentToBeUpdated.getId().equals(dto.id())) {
            paymentToBeUpdated.setValue(dto.value());
            paymentToBeUpdated.setPeriod(dto.period());
            paymentToBeUpdated.setExpiration(dto.expiration());
            paymentToBeUpdated.setStatus(dto.status());

            return new PaymentDto(repository.save(paymentToBeUpdated));
        }
        return null;
    }

    @Transactional
    public void deletePayment(@NotNull Long id) {
        var paymentToBeDeleted = repository.getReferenceById(id);

        if (paymentToBeDeleted.getId().equals(id)) {
            repository.delete(paymentToBeDeleted);
        }

    }
}
