package com.svj.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svj.dto.Order;
import com.svj.dto.User;
import com.svj.entity.Payment;
import com.svj.repository.PaymentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Component
public class OrderProcessingConsumer {

    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;
    private PaymentRepository repository;
    String USER_SREVICE_URL = "http://localhost:9393/users/";

    public OrderProcessingConsumer(ObjectMapper objectMapper, RestTemplate restTemplate, PaymentRepository repository){
        this.objectMapper= objectMapper;
        this.restTemplate= restTemplate;
        this.repository= repository;
    }

    @KafkaListener(topics = "ORDER_PAYMENT_TOPIC")
    public void processOrder(String orderJsonString){
        try {
            Order order = objectMapper.readValue(orderJsonString, Order.class);
            // build payment request without payment status. For Cash on Delivery payment will be success only after cash collection.
            Payment payment = Payment.builder()
                    .payMode(order.getPaymentMode())
                    .amount(order.getPrice())
                    .paidDate(LocalDate.now())
                    .userId(order.getUserId())
                    .orderId(order.getOrderId())
                    .build();
            if(payment.getPayMode().equals("COD")){
                payment.setPaymentStatus("PENDING");
            }else {
                // validate available amount
                User user = restTemplate.getForObject(USER_SREVICE_URL + payment.getUserId(), User.class);
                if(payment.getAmount()> user.getAvailableAmount()){
                    throw new RuntimeException("Insufficient amount!");
                }else{
                    payment.setPaymentStatus("PAID");
                    restTemplate.put(USER_SREVICE_URL+payment.getUserId()+"/"+payment.getAmount(), null);
                }

            }
            repository.save(payment);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // log.error
        }
    }
}
