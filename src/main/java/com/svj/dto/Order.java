package com.svj.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private int id;
    private String name;
    private String category;
    private double price;
    private LocalDate purchaseDate;
    //    private Date purchaseDate;
    private String orderId;
    private int userId;
    private String paymentMode;


//    @JsonCreator
//    public static Order of(@JsonProperty("id") int id,
//                           @JsonProperty("name") String name,
//                           @JsonProperty("category") String category,
//                           @JsonProperty("price") double price,
//                           @JsonProperty("purchaseDate") LocalDate purchaseDate,
//                           @JsonProperty("orderId") String orderId,
//                           @JsonProperty("userId") int  userId,
//                           @JsonProperty("paymentMode") String paymentMode
//    ) {
//        Order order = new Order(id, name, category, price, purchaseDate, orderId, userId, paymentMode);
//        return order;
//    }
}
