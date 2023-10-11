package com.svj.service;

import com.svj.entity.Payment;
import com.svj.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository repository;

    public PaymentService(PaymentRepository repository){
        this.repository= repository;
    }

    public Payment getByOrderId(String orderId){
        return repository.findByOrderId(orderId);
    }
}
