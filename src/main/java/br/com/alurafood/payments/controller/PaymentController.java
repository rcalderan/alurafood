package br.com.alurafood.payments.controller;

import br.com.alurafood.payments.dto.CreatePaymentDto;
import br.com.alurafood.payments.dto.PaymentDto;
import br.com.alurafood.payments.dto.UpdatePaymentDto;
import br.com.alurafood.payments.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @GetMapping
    public Page<PaymentDto> listAll(@PageableDefault(size = 10)Pageable pageable){
        return paymentService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable @NotNull Long id){
        var dto = paymentService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody @Valid CreatePaymentDto dto, UriComponentsBuilder uriComponentsBuilder){
        PaymentDto payment = paymentService.createPayment(dto);

        URI path = uriComponentsBuilder.path("/payments/{id}").buildAndExpand(payment.id()).toUri();

        return ResponseEntity.created(path).body(payment);
    }

    @PutMapping
    public ResponseEntity<PaymentDto> update(@RequestBody @Valid UpdatePaymentDto dto){
        PaymentDto updated = paymentService.updatePayment(dto);
        return updated == null ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDto> deletePayment(@PathVariable @NotNull Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
