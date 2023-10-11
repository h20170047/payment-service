package com.svj.controller;

import com.svj.entity.Payment;
import com.svj.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService service;

    public PaymentController(PaymentService service){
        this.service= service;
    }

    @GetMapping("/{orderId}")
    public Payment getPayment(@PathVariable String orderId){
        return service.getByOrderId(orderId);
    }
}
